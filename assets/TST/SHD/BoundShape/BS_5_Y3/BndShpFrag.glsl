#ifdef GL_ES
precision mediump float;
#endif

#define PI 3.14159265359
#define TWO_PI 6.28318530718

varying vec4 v_color;   
varying vec2 v_texCoords;
varying vec3 c_position; //camera position

uniform mat4 u_projTrans; //camera combined matrix
uniform vec2 u_resolution;
uniform vec2 u_mouse; //mouse position
uniform float u_time;



//////////////////////////////////////////////////
vec2 mouseAt;
vec2 camAt;
vec2 scrnOrig;

//BS
uniform int shpCnt;
uniform vec3 posAr[1];

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
	
	//posAr[0] = vec3(mouseAt.xy,1);//works
}










void main(){



setMoCam();
	
	vec2 st = (gl_FragCoord.xy/u_resolution);
	vec4 color = vec4(0);
	float pct = 0.0;
	vec2 size = vec2(32,32)/u_resolution;
	
	
	float d = 0;
	for(int i =0; i<posAr.length(); i++)
	{
	int n = (i+1)%posAr.length();
	 pct = (distance((posAr[i].xy/u_resolution),mouseAt)-0.1)-d;
		if(pct >= 0.1)
		{
		vec3 c = vec3(pct);
		color = vec4(c,1);
		
		}
		else
		{
		 pct = (length(posAr[n].xy/u_resolution)-0.1)-d;
		color = vec4(0);
		}
	}

	
	gl_FragColor = vec4(color);
	

     
}