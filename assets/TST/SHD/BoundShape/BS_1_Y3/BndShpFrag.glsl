#ifdef GL_ES
precision mediump float;
#endif

#define PI 3.14159265359
#define TWO_PI 6.28318530718

varying vec4 v_color;   
varying vec2 v_texCoord0;
varying vec3 c_position; //camera position

uniform mat4 u_projTrans; //camera combined matrix

uniform vec2 u_resolution;
uniform vec2 u_mouse; //mouse position
uniform float u_time;



void main(){

	//vec2 st = (gl_FragCoord.xy/u_resolution);
	//vec2 m = u_mouse/u_resolution;
	
	
	//camera & mouse positions
	vec3 c = c_position;
	vec2 cPos = c.xy/u_resolution;
	
	vec2 st = cPos;		
	vec2 m = u_mouse/u_resolution;
	
	//adjust to aspect ratio
	st.x *= u_resolution.x/u_resolution.y;
	m.x *= u_resolution.x/u_resolution.y;
	
	
	
	vec3 color = vec3(0.0);
	float d = 0.0;

	
    // Number of sides of your shape
     int N = 6;

    // Angle and radius from the current pixel
    // float a = u_time*atan(st.x-m.x,st.y-m.y)+PI; //expand vertices from 0-N
    // float a = u_time+atan(st.x-m.x,st.y-m.y)+PI; //rotates
        float a = u_time+atan(st.x-m.x,st.y-m.y)+PI;
     float r = TWO_PI/float(N);

    // Shaping function that modulate the distance
     d = cos(floor(.5+a/r)*r-a)*length(st-m);

     color = vec3(1.0-smoothstep(.4,.41,d));
    // color = vec3(d);

     gl_FragColor = vec4(color,1.0);
}