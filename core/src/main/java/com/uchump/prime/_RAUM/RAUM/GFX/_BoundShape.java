package com.uchump.prime._RAUM.RAUM.GFX;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME.C_O.Geom;
import com.uchump.prime._PRIME.C_O.VectorUtils;
import com.uchump.prime._PRIME.C_O.NIX.BoundShape;
import com.uchump.prime._PRIME.C_O.NIX.Transform;
import com.uchump.prime._PRIME.N_M._N.Node;
import com.uchump.prime._PRIME.SYS.NMP._Component;
import com.uchump.prime._PRIME.UI._GFX.Drawable.iDrawable;
import com.uchump.prime._RAUM.RAUM.GFX.Prototype.Line;

public class _BoundShape extends _Component implements iDrawable {

	BoundShape x;
	public Transform Transform;

	public Color fillColor = Color.WHITE;
	public Color lineColor = Color.GRAY;
	public int vertexNum = 0;
	public float vertexSize = 2;
	public float lineWidth = 1;

	public ArrayList<Vector3> shape = new ArrayList<Vector3>();
	protected int OriginIndex = 0; // if 0, return position

	private boolean dirty = false;
	public boolean debug = true;
	public boolean fill = false;
	public boolean lines = true;
	public boolean linesOutter = true;
	public boolean linesInner = true;

	public Array<Line> lns = new Array<Line>();

	public _BoundShape() {
		super("BoundShape", new Type(false, "iDrawable"));
		this.Transform = new Transform();
	}

	@Override
	public void draw() {
		int o = 0;
		int n = 0;
		int s = this.shape.size() - 1;
		Vector3 next;
		Vector3 last;
		Vector3 position = this.position();
		// Log(position + " >> ");
		for (int i = 0; i < s + 1; i++) {
			Vector3 v = this.shape.get(i);
			Sketcher.setColor(this.lineColor);

			int f = s + 1;
			n = i + 1;
			if (n >= f)
				n = (n % f);

			next = this.shape.get(n);

			if (this.lines) {
				if (this.linesOutter) {

					if (!this.lns.isEmpty()) {
						for (int l = 0; l < this.lns.size; l++) {
							Line L = this.lns.get(i);
							L.draw();
						}
					}
					// Sketcher.Drawer.line(new Vector2(next.x, next.y), new Vector2(v.x, v.y),
					// this.lineColor,
					// this.lineWidth);
				}
				if (this.linesInner) {
					Sketcher.Drawer.line(new Vector2(v.x, v.y), new Vector2(position.x, position.y), this.lineColor,
							this.lineWidth);
				}
			}

			Sketcher.setColor(this.fillColor);
			if (this.fill)
				Sketcher.Drawer.filledPolygon(this.toPolygon());
			Sketcher.Drawer.filledCircle(v.x, v.y, this.vertexSize + (this.vertexSize % v.z));

		}

		if (!this.shape.isEmpty()) {
			last = this.shape.get(0);
			Sketcher.setColor(new Color(1, 1, 1, 0.5f));
			Sketcher.Drawer.filledCircle(last.x, last.y, this.vertexSize);
		}

	}

	public float perimeter() {
		float sum = 0f;
		for (int i = 0; i < this.vertexNum; i++) {
			sum = sum + this.shape.get(i).dst(this.shape.get(i + 1));
		}
		return sum;
	}

	public double area() {
		double sum = 0.0;
		for (int i = 0; i < this.vertexNum; i++) {
			sum = sum + (this.shape.get(i).x * this.shape.get(i + 1).y)
					- (this.shape.get(i).y * this.shape.get(i + 1).x) - (this.shape.get(i).z * this.shape.get(i + 1).z);
		}
		return 0.5 * sum;
	}

	public boolean overlaps(_BoundShape B) {
		return Intersector.overlapConvexPolygons(this.toPolygon(), B.toPolygon());
	}

	////////

	public static _BoundShape defVertex(Vector3 at, float radius, Color c) {
		_BoundShape newShape = new _BoundShape();
		newShape.Transform.SetLocalPosition(at);
		newShape.Transform.SetLocalScale(new Vector3().add(radius));
		newShape.vertexNum = 1;
		newShape.vertexSize = radius;
		newShape.lines = false;
		newShape.setColor(c);
		newShape.shape.add(at);
		newShape.genLines();
		return newShape;
	}

	public static _BoundShape bindVertex(Transform t, float radius, Color c) {
		_BoundShape newShape = new _BoundShape();
		newShape.Transform = (Transform) t.cpy();
		newShape.vertexNum = 1;
		newShape.vertexSize = radius;
		newShape.lines = false;
		newShape.setColor(c);
		newShape.shape.add(t.GetLocalPosition());
		newShape.genLines();

		return newShape;
	}

	public static _BoundShape defLine(Vector3 origin, Vector3 to) {
		_BoundShape newShape = new _BoundShape();

		newShape.shape = Geom.lineTo(origin, to);
		newShape.Transform.SetLocalPosition(origin.cpy().sub(to.cpy()));
		newShape.Transform.SetLocalRotation(VectorUtils.upcast(origin.cpy().sub(to.cpy()).nor()));
		newShape.vertexNum = 2;
		newShape.vertexSize = 1;
		newShape.lines = true;
		newShape.shape.add(origin);
		newShape.shape.add(to);
		newShape.genLines();

		return newShape;
	}

	public static _BoundShape defRadius(Vector3 at, Vector3 length, Vector3 rotation, Color color) {
		return defShape(at, length, rotation, color, 1, true);
	}

	public static _BoundShape defShape(Vector3 at, Vector3 radius, Vector3 rotation, Color c, int n) {
		return defShape(at, radius, rotation, c, n, false);
	}

	public static _BoundShape defShape(Vector3 at, Vector3 radius, Vector3 rotation, Color c, int n, boolean index) {
		_BoundShape newShape = new _BoundShape();

		newShape.Transform.SetPosition(at);
		newShape.Transform.SetScale(radius);
		newShape.Transform.SetRotation(VectorUtils.upcast(rotation));
		newShape.fillColor = c.cpy();
		newShape.lineColor = c.cpy();
		newShape.vertexNum = n;

		newShape.shape = Geom.generatePolygon(at, radius, rotation, n);
		if (index)
			for (Vector3 V : newShape.shape) {
				V.x = MathUtils.round(V.x);
				V.y = MathUtils.round(V.y);
				V.z = MathUtils.round(V.z);
			}

		newShape.genLines();
		return newShape;

	}
	
	public _BoundShape rebind(Transform t)
	{
		
		this.Transform = t;
		Vector3 pos = t.GetLocalPosition();
		Vector3 rad = t.GetLocalScale();
		Vector3 rot = VectorUtils.downcast(t.GetLocalRotation());

		this.shape.clear();
		this.lns.clear();
		
		this.shape = Geom.generatePolygon(pos, rad, rot, this.vertexNum);
		
		this.genLines();
		return this;
	}

	public static _BoundShape bindShape(Transform transform, Color c, int n, boolean index) {
		_BoundShape newShape = new _BoundShape();

		newShape.Transform = transform.cpy();

		newShape.fillColor = c.cpy();
		newShape.lineColor = c.cpy();
		newShape.vertexNum = n;

		Vector3 pos = transform.GetLocalPosition();
		Vector3 rad = transform.GetLocalScale();
		Vector3 rot = VectorUtils.downcast(transform.GetLocalRotation());

		newShape.shape = Geom.generatePolygon(pos, rad, rot, n);
		if (index)
			for (Vector3 V : newShape.shape) {
				V.x = MathUtils.round(V.x);
				V.y = MathUtils.round(V.y);
				V.z = MathUtils.round(V.z);
			}

		newShape.genLines();
		return newShape;
	}

	public _BoundShape bindPoly(Polygon p) {

		ArrayList<Vector2> vert = VectorUtils.assembleVect2(p.getTransformedVertices());

		Vector2 pos = new Vector2(p.getX(), p.getY());
		Vector2 size = new Vector2(p.getScaleX(), p.getScaleY());
		Vector3 position = VectorUtils.upcast(pos);
		Vector3 scale = VectorUtils.upcast(size);
		ArrayList<Vector3> shape = new ArrayList<Vector3>();
		for (int i = 0; i < vert.size(); i++) {
			shape.add(VectorUtils.upcast(vert.get(i)));
		}

		_BoundShape newShape = new _BoundShape();
		newShape.Transform.SetLocalPosition(position);
		newShape.Transform.SetLocalScale(scale);
		newShape.shape = shape;

		newShape.genLines();
		return newShape;
	}
	////////////////

	public void genLines() {
		for (int i = 0; i < this.shape.size(); i++) {
			int n = i + 1;
			if (i == this.shape.size() - 1)
				n = 0;

			Line nL = new Line(this.shape.get(i), this.shape.get(n));
			nL.fillColor = this.lineColor;
			nL.lineColor = this.lineColor;
			nL.vertexSize = nL.len() / nL.shape.size();
			this.lns.add(nL);
		}
	}

	public void setColor(Color c) {
		this.fillColor = c;
		this.lineColor = c;
	}

	public void origin(int index) {
		this.OriginIndex = index;
	}

	public void position(Vector3 at) {
		this.Transform.SetLocalPosition(at);
	}

	public void rotation(Vector3 rot) {
		this.Transform.SetLocalRotation(VectorUtils.upcast(rot));
	}

	public void rotation(Quaternion rot) {
		this.Transform.SetLocalRotation(rot);
	}

	public void scale(Vector3 scl) {
		this.Transform.SetLocalScale(scl);
	}

	public Vector3 origin() {
		if (this.OriginIndex == 0)
			return this.position();
		if (this.OriginIndex > 0)
			return this.shape.get(this.OriginIndex);
		if (this.OriginIndex < 0) {
			return this.shape.get(this.shape.size() + this.OriginIndex);
		}
		return this.position();
	}

	public Vector3 position() {
		return this.Transform.GetLocalPosition();
	}

	public Quaternion rotation() {
		return this.Transform.GetLocalRotation();
	}

	public Vector3 direction() {
		Quaternion q = this.rotation();
		return new Vector3(q.x, q.y, q.z);
	}

	public Vector3 scale() {
		return this.Transform.GetLocalScale();
	}

	public boolean contains(Vector2 v) {

		return contains(v.x, v.y);
	}

	public boolean epsContains(Vector2 v, float e) {

		return this.contains(v.x + e, v.y + e) || this.contains(v.x - e, v.y - e) || this.contains(v.x + e, v.y - e)
				|| this.contains(v.x - e, v.y + e);
	}

	public boolean contains(float x, float y) {

		return (this.toPolygon().contains(x, y));

	}

	public float umDst(Vector3 v)
	{
		if(this.epsContains(VectorUtils.downcast(v), 1f))
		{
			Vector3 r = VectorUtils.dst(this.Transform.GetLocalPosition(), v);
			return r.len();
		}
		else
			return Float.NaN;
	}

	public Polygon toPolygon() {

		ArrayList<Vector2> tmpVerts = new ArrayList<Vector2>();
		for (Vector3 v : this.shape) {
			tmpVerts.add(VectorUtils.downcast(v.cpy()));
		}

		Polygon result = new Polygon(VectorUtils.disassembleVects(tmpVerts.toArray()));

		if (this.Transform != null) {
			Vector3 scl = this.Transform.GetLocalScale();
			result.setOrigin(this.Transform.GetLocalPosition().x, this.Transform.GetLocalPosition().y);
			Quaternion rot = this.Transform.GetLocalRotation();
			result.setRotation((float) Math.atan2(rot.x, rot.y));
		}

		return result;
	}

	public Vector3 intersectLine(Line at) {
		return this.intersectLine(at, true);
	}

	public Vector3 intersectLine(Line at, boolean toFrom) {
		// Array<Line> tmpLines = new Array<Line>(true, 0, Line.class);
		// for (int i = 0; i < this.shape.size(); i++) {
		// int n = i + 1;
		// if (i == this.shape.size() - 1)
		// n = 0;
		// tmpLines.add(new Line(this.shape.get(i), this.shape.get(n)));
		// }

		// add all intersecting points to intersection
		// final intersection is closest to origin

		Vector3 origin = at.getOrigin();
		Vector3 end = at.getEnd();

		Array<Vector3> intersections = new Array<Vector3>(true, 0, Vector3.class); // solution set

		for (Line l : this.lns) {
			Vector3 t = l.intersectAt(at, toFrom);//
			if (t != null) {
				intersections.add(t);
			}
		}

		if (intersections.isEmpty())
			return null; // return intersections in a seperate fn lineIntersections or something

		Vector3 c = null;
		float d = Float.MAX_VALUE;
		for (Vector3 v : intersections) {
			float dst = VectorUtils.dst(origin.cpy(), v.cpy()).len();
			if (dst < d) {
				d = dst;
				c = v;
			}
		}

		return c;
	}

	public Array<Vector3> intersectsLine(Line at) {
		Vector3 origin = at.getOrigin();
		Vector3 end = at.getEnd();

		Array<Vector3> intersections = new Array<Vector3>(true, 0, Vector3.class);
		for (Line l : this.lns) {
			Array<Vector3> hits = l.intersectsAt(at);
			if (hits != null) {

				intersections.addAll(hits);

			}
		}
		if (intersections.isEmpty())
			return null;

		return intersections;

	}

	public _BoundShape cpy() {
		_BoundShape B = new _BoundShape();
		B.Transform.SetLocalPosition(this.position());
		B.Transform.SetLocalRotation(this.rotation());
		B.Transform.SetLocalScale(this.scale());

		B.lineColor = this.lineColor;
		B.fillColor = this.fillColor;
		B.vertexNum = this.vertexNum;
		B.vertexSize = this.vertexSize;
		B.lineWidth = this.lineWidth;

		for (Vector3 V : this.shape) {
			B.shape.add(V.cpy());
		}

		if (this.owner != null)
			B.owner = this.owner;

		return B;
	}

	@Override
	public String toString() {
		return this.Name() + ":" + this.get() + "   " + this.Class().getSimpleName() + this.Node + ":" + this.Type();
	}

	@Override
	public String toLog() {
		String log = this.toString() + "\n";
		String ind = "";
		for (int i = 0; i < this.Name().length(); i++)
			ind += " ";

		log += this.position() + " :: " + this.rotation() + " :: " + this.scale() + "\n";
		if (!this.Properties.isEmpty()) {

			log += ind + ":{Properties}:\n";
			for (Node p : this.Properties) {
				log += ind + "  " + p.toLog() + "\n";
			}
		}

		return log;
	}

}
