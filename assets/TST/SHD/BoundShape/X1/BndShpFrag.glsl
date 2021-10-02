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


float plot(vec2 st, float pct){


  return  smoothstep( pct-0.02, pct, st.y) -
          smoothstep( pct, pct+0.02, st.y);
}

void main(){


	//vec2 m = u_mouse/u_resolution;
	
	
	

	vec3 c = c_position;
	vec2 cPos = c.xy/u_resolution;
	//vec2 st = (gl_FragCoord.xy/u_resolution);
	vec2 st = cPos;
	
	vec2 m = u_mouse/u_resolution;
	
	
    float y = step(0.5,st.x-m.x);

    vec3 color = vec3(y);

    float pct = plot(st-m,y);
    color = (1.0-pct)*color+pct*vec3(0.0,1.0,0.0);

    gl_FragColor = vec4(color,1.0);
    
}