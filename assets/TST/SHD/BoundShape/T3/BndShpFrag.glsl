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




void main(){

	//vec2 m = u_mouse/u_resolution;
	
	//vec2 st = (gl_FragCoord.xy/u_resolution);//use cPos instead!!!
	vec2 cPos = c_position.xy/u_resolution;
	
	vec2 st = cPos;
	
  vec3 color = vec3(0.0);
  float d = 0.0;

  // Remap the space to -1. to 1.
  st = st *2.-1.;

  // Make the distance field
  d = length( abs(st)-.3 );
  // d = length( min(abs(st)-.3,0.) );
  // d = length( max(abs(st)-.3,0.) );

  // Visualize the distance field
    vec4 df = vec4(vec3(fract(d*10.0)),1.0);  
    //gl_FragColor = vec4(df.x*(u_mouse.x/4),df.y*(u_mouse.y/4),df.z*(u_mouse.x+u_mouse.y),df.w);
	gl_FragColor = df;


  // Drawing with the distance field
  // gl_FragColor = vec4(vec3( step(.3,d) ),1.0);
  // gl_FragColor = vec4(vec3( step(.3,d) * step(d,.4)),1.0);
  // gl_FragColor = vec4(vec3( smoothstep(.3,.4,d)* smoothstep(.6,.5,d)) ,1.0);
}