#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;   
varying vec2 v_texCoord0;

varying vec3 c_position;

uniform mat4 u_projTrans;

uniform vec2 u_resolution;
uniform vec2 u_mouse;
uniform float u_time;


float plot(vec2 st) {    
    return smoothstep(0.02, 0.0, abs(st.y - st.x));
}


void main(){

	
	vec2 cPos = c_position.xy/u_resolution;
	//vec2 st = (gl_FragCoord.xy/u_resolution);//use cPos instead!!!
	
	
	vec2 st = cPos;
	
      vec3 color = vec3(0.0);

    // bottom-left
    vec2 bl = step(vec2(0.1),st);
    float pct = bl.x * bl.y;

    // top-right
     vec2 tr = step(vec2(0.1),1.0-st);
    pct *= tr.x * tr.y;

    color = vec3(pct);

    gl_FragColor = vec4(color,1.0);
}