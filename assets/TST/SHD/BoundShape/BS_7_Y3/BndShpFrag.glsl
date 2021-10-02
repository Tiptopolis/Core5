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
uniform vec3 posAr[1000];

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
	float d =0.0;
	
	float D =0;
	
	//for(int i =0; i < posAr.length(); i++)
	for(int i =0; i < shpCnt; i++)
	{
	vec3 pA = posAr[i];
		float scn = distance(posAr[i]/u_resolution,mouseAt)-0.1;
		 d = length( abs(posAr[i]/u_resolution)-.3 );
		
		D+=min(d,scn);
		
	}
	pct = D;
 	//pct = length(mouseAt)-0.1f;
	vec3 c = vec3(pct);
	color = vec4(c,1);
	//gl_FragColor = vec4(color);
	gl_FragColor = vec4(vec3(fract(D*10.0)),1.0);

     
}