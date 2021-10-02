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

	vec3 c = c_position;
	vec2 cPos = c.xy/u_resolution;
	//vec2 st = (gl_FragCoord.xy/u_resolution);
	vec2 st = cPos;
	
    float pct = 0.0;

    // a. The DISTANCE from the pixel to the center
    pct = distance(st,vec2(0.5));

    // b. The LENGTH of the vector
    //    from the pixel to the center
    // vec2 toCenter = vec2(0.5)-st;
    // pct = length(toCenter);

    // c. The SQUARE ROOT of the vector
    //    from the pixel to the center
    // vec2 tC = vec2(0.5)-st;
    // pct = sqrt(tC.x*tC.x+tC.y*tC.y);

    vec3 color = vec3(pct);

	gl_FragColor = vec4( color, 1.0 );
}