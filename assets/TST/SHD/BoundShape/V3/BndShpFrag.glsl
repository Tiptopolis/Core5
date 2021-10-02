#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;



uniform mat4 u_projTrans;
uniform vec2 u_resolution;
uniform vec3 u_mouse;
uniform float u_time;

float plot(vec2 st) {    
    return smoothstep(0.02, 0.0, abs(st.y - st.x));
}

void main() {

  vec4 m = vec4(u_mouse.x,u_mouse.y,u_mouse.z,0)*u_projTrans; //properly projects
  
  
	float t = u_time;
	//gl_FragColor = vec4(m.x,m.y,m.z,1);
	
	vec4 at = vec4(gl_FragCoord.x,gl_FragCoord.y,1,1)*u_projTrans;
  	vec2 st = at.xy;

    float y = st.x;

    vec3 color = vec3(y);

    // Plot a line
    float pct = plot(st);
    color = (1.0-pct)*color+pct*vec3(0.0,1.0,0.0);

	gl_FragColor = vec4(color,1.0);

}


