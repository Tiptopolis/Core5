#ifdef GL_ES
precision mediump float;
#endif

#define PI 3.14159265359
#define TWO_PI 6.28318530718

varying vec4 v_color;   
varying vec2 v_texCoord0;

varying vec3 c_position;

uniform mat4 u_projTrans;

uniform vec2 u_resolution;
uniform vec2 u_mouse;
uniform float u_time;


//
//
// Eye-Leaf-Lemon
//
//


float sdCircle( vec2 p, float r )
{
    return length(p) - r;
}

float sdHexagon( in vec2 p, in float r )
{
    const vec3 k = vec3(-0.866025404,0.5,0.577350269);
    p = abs(p);
    p -= 2.0*min(dot(k.xy,p),0.0)*k.xy;
    p -= vec2(clamp(p.x, -k.z*r, k.z*r), r);
    return length(p)*sign(p.y);
}

float sdBlobbyCross( in vec2 pos, float he )
{
    pos = abs(pos);
    pos = vec2(abs(pos.x-pos.y),1.0-pos.x-pos.y)/sqrt(2.0);

    float p = (he-pos.y-0.25/he)/(6.0*he);
    float q = pos.x/(he*he*16.0);
    float h = q*q - p*p*p;
    
    float x;
    if( h>0.0 ) { float r = sqrt(h); x = pow(q+r,1.0/3.0)-pow(abs(q-r),1.0/3.0)*sign(r-q); }
    else        { float r = sqrt(p); x = 2.0*r*cos(acos(q/(p*r))/3.0); }
    x = min(x,sqrt(2.0)/2.0);
    
    vec2 z = vec2(x,he*(1.0-2.0*x*x)) - pos;
    return length(z) * sign(z.y);
}

void main(){


	//vec2 m = u_mouse/u_resolution;
	
	
	

	vec3 c = c_position;
	vec2 cPos = c.xy/u_resolution;
	//vec2 st = (gl_FragCoord.xy/u_resolution);
	vec2 st = cPos;
	
	vec2 m = u_mouse/u_resolution;
	
	
	
	vec3 color = vec3(0.0);
	//float d = length(m-cPos);
	//float d = sdCircle(m-cPos, 0.1);
	
	
	float a = atan(st.x,st.y)+PI;
    float r = TWO_PI/float(360);
	float d = cos(floor(.5+a/r)*r-a)*length(m-st);
	
	color = vec3(1.0-smoothstep(.4,.41,d));
	
	gl_FragColor = vec4(color,1.0);
  
}