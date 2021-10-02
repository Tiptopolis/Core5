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







//////////////////////////////////////////////////
vec2 mouseAt;
vec2 camAt;
vec2 scrnOrig;

void setMoCam()
{
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
	
	mouseAt = st-m;
	camAt = st;
	scrnOrig = (gl_FragCoord.xy/u_resolution);
	
}







float plot(vec2 st) {    
    return smoothstep(0.02, 0.0, abs(st.y - st.x));
}


void main(){

setMoCam();
	
	
	float y = mouseAt.x;

    vec3 color = vec3(y*u_time);
	
	    // Plot a line
    float pct = plot(mouseAt);
    color = (1.0-pct)*color+pct*vec3(0.0,1.0,0.0);

	gl_FragColor = vec4(color,1.0);
	

     
}