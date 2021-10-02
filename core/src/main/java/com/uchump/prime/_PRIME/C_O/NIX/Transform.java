package com.uchump.prime._PRIME.C_O.NIX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.SYS.A_I.aProperty;
import com.uchump.prime._PRIME.SYS.NMP._Component;
import com.uchump.prime._PRIME.SYS.NMP._Entity;

/**
 * 3D transform system similar to Unity3D for libGDX
 * 
 * @author Tim Aksu - timur.s.aksu@gmail.com
 */
public class Transform extends _Component {

	private static final String TAG = "Transform";

	protected Vector3 _localPosition;
	protected Quaternion _localRotation;
	protected Vector3 _localScale;

	protected Transform _parent;
	protected Array<Transform> _children;
	protected Matrix4 _localToWorldMatrix;

	//
	protected int depth = -1;

	public Transform() {
		super("Transform", _Component.Component);
		_localPosition = new Vector3(0, 0, 0);
		_localRotation = new Quaternion();
		_localScale = new Vector3(1, 1, 1);

		_parent = null;
		_children = new Array<Transform>();
		_localToWorldMatrix = new Matrix4(_localPosition, _localRotation, _localScale);
		
		this.Label = "Transform";
		this.Node = _Component.Component;
		

		this.addProperty(new aProperty("Position", this._localPosition));
		this.addProperty(new aProperty("Rotation", this._localRotation));
		this.addProperty(new aProperty("Scale", this._localScale));

		this.addProperty(new aProperty("Children", this._children));
		this.addProperty(new aProperty("TransformMatrix", this._localToWorldMatrix));
	}
	
	public Transform(_Entity owner)
	{
		super("Transform", _Component.Component);
		this.owner = owner;
		_localPosition = new Vector3(0, 0, 0);
		_localRotation = new Quaternion();
		_localScale = new Vector3(1, 1, 1);

		_parent = null;
		_children = new Array<Transform>();
		_localToWorldMatrix = new Matrix4(_localPosition, _localRotation, _localScale);
		
		this.Label = "Transform";
		this.Node = _Component.Component;
		

		this.addProperty(new aProperty("Position", this._localPosition));
		this.addProperty(new aProperty("Rotation", this._localRotation));
		this.addProperty(new aProperty("Scale", this._localScale));

		this.addProperty(new aProperty("Children", this._children));
		this.addProperty(new aProperty("TransformMatrix", this._localToWorldMatrix));
	}

	/**
	 * Matrix that transforms a point from local space into world space
	 * 
	 * @return A copy of this transforms local to world matrix
	 */
	public Matrix4 GetLocalToWorldMatrix() {
		return _localToWorldMatrix.cpy();
	}

	/**
	 * @return The current position in local space
	 */
	public Vector3 GetLocalPosition() {
		return _localPosition.cpy();
	}

	/**
	 * @return The current rotation in local space
	 */
	public Quaternion GetLocalRotation() {
		return _localRotation.cpy();
	}

	/**
	 * @return The current scale in local space
	 */
	public Vector3 GetLocalScale() {
		return _localScale.cpy();
	}

	/**
	 * @return The current position in world space
	 */
	public Vector3 GetPosition() {
		Vector3 result = new Vector3();
		_localToWorldMatrix.getTranslation(result);
		return result;
	}

	/**
	 * @return The current rotation in world space
	 */
	public Quaternion GetRotation() {
		Quaternion result = new Quaternion();
		_localToWorldMatrix.getRotation(result, true);
		return result;
	}

	/**
	 * @return The current scale in world space
	 */
	public Vector3 GetScale() {
		Vector3 result = new Vector3();
		_localToWorldMatrix.getScale(result);
		return result;
	}

	/**
	 * @param position The new local position
	 */
	public void SetLocalPosition(Vector3 position) {
		_localPosition.set(position);
		UpdateMatricies();
	}

	/**
	 * @param rotation The new local rotation
	 */
	public void SetLocalRotation(Quaternion rotation) {
		_localRotation.set(rotation);
		UpdateMatricies();
	}

	/**
	 * @param scale The new local scale
	 */
	public void SetLocalScale(Vector3 scale) {
		_localScale.set(scale);
		UpdateMatricies();
	}

	/**
	 * @param position The world position to match in local space
	 */
	public void SetPosition(Vector3 position) {
		if (_parent == null)
			_localPosition.set(position);
		else
			_localPosition.set(position.mul(_parent.GetLocalToWorldMatrix().inv()));

		UpdateMatricies();
	}

	/**
	 * @param rotation The world rotation to match in local space
	 */
	public void SetRotation(Quaternion rotation) {
		if (_parent == null) {
			_localRotation.set(rotation);
		} else {
			Quaternion temp = new Quaternion();
			_localRotation.set(rotation.mul(_parent.GetLocalToWorldMatrix().inv().getRotation(temp, true)));
		}

		UpdateMatricies();
	}

	/**
	 * @param scale The world scale to match in local space
	 */
	public void SetScale(Vector3 scale) {
		if (_parent == null)
			_localScale.set(scale);
		else
			_localScale.set(scale.mul(_parent.GetLocalToWorldMatrix().inv()));

		UpdateMatricies();
	}

	/**
	 * @return Current Root of the Transform hierarchy that this Transform exists in
	 */
	public Transform GetRoot() {
		if (_parent == null)
			return this;
		else
			return _parent.GetRoot();
	}

	/**
	 * @param target The transform to check
	 * @return True if this transform is a child of target (directly or indirectly)
	 */
	public boolean IsChildOf(Transform target) {
		boolean isChild = false;

		for (Transform child : target._children) {
			if (child == this)
				isChild = true;
			else
				isChild = IsChildOf(child);

			if (isChild)
				break;
		}

		return isChild;
	}

	/**
	 * @return The current parent (null if no parent)
	 */
	public Transform GetParent() {
		return _parent;
	}

	/**
	 * Sets the parent. Also modifies the transform to match its world space
	 * transform in its new local space.
	 * 
	 * Calls RemoveParent if current parent is not null.
	 * 
	 * Circular parenting will log an error and not do anything. This will result in
	 * an error: A->B->C->A
	 * 
	 * @param to The transform to parent to
	 */
	public void SetParent(Transform to) {
		if (to.IsChildOf(this)) {
			Gdx.app.error(TAG, "Prevented circular parenting");
			return;
		}

		if (_parent != null)
			RemoveParent();

		Matrix4 parentWorldToLocalMatrix = to.GetLocalToWorldMatrix().inv().mul(GetLocalToWorldMatrix());

		parentWorldToLocalMatrix.getTranslation(_localPosition);
		parentWorldToLocalMatrix.getRotation(_localRotation, true);
		parentWorldToLocalMatrix.getScale(_localScale);

		this.get("Position").set(this.GetLocalPosition());
		this.get("Rotation").set(this.GetLocalRotation());
		this.get("Scale").set(this.GetLocalScale());
		this.get("TransformMatrix").set(this.GetLocalToWorldMatrix());
		
		to._children.add(this);
		_parent = to;
		to.UpdateMatricies();

		this.depth = this._parent.depth + 1;
		if (this.get("Parent") == null)
			this.addProperty(new aProperty("Parent", this._parent));
	}

	/**
	 * Removed the current parent. Also modifies the transform to match its old
	 * local space transform in world space
	 */
	public void RemoveParent() {

		if (_parent == null) {
			return;
		} else {
			_localPosition = GetPosition();
			_localRotation = GetRotation();
			_localScale = GetScale();

			_parent._children.removeValue(this, true);
			_parent.get("Children").set(_parent._children);
			_parent = null;

			UpdateMatricies();
		}
	}

	protected void UpdateMatricies() {
		_localToWorldMatrix = new Matrix4(_localPosition, _localRotation, _localScale);

		if (_parent != null) {

			_localToWorldMatrix = new Matrix4(_parent._localToWorldMatrix).mul(_localToWorldMatrix);
		}

		for (Transform child : _children) {
			child.UpdateMatricies();
		}
	}

	
	public Transform cpy()
	{
		Transform T = new Transform();
		T.SetPosition(this._localPosition);
		T.SetRotation(this._localRotation);
		T.SetScale(this._localScale);
		return T;
	}
	
	@Override
	public String toString() {
		return this.Name() + "_" + this.Node + " @" + this.Class().getSimpleName() + "[" + this.Type() + "]";
		// REFERENCE NODE INDEX SYMBOL
	}

	@Override
	public String toLog() {
		String log = this.toString() + "\n";
		log += "[<"+this.Label + ">]\n";
		log += this._localToWorldMatrix.toString() + "\n";
		for (Node N : this.Properties) {
			log += N + "\n";

		}

		return log;
	}

}