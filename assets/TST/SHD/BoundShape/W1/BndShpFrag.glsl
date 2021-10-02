#ifdef GL_ES
precision mediump float;
#endif



uniform mat4 u_projTrans;
uniform vec2 u_resolution;
uniform vec2 u_mouse;
uniform float u_time;

uniform vec3 u_camP;




void main(){


//vec4 st = vec4(gl_FragCoord.x-u_camP.x,gl_FragCoord.y-u_camP.y, 0.0, 1.0); //not blured
//vec4 st = vec4((gl_FragCoord.x-u_camP.x)/u_resolution.x,(gl_FragCoord.y-u_camP.y)/u_resolution.y, 0.0, 1.0); //blured

//vec2 st = gl_FragCoord.xy/u_resolution;
//vec2 st = (gl_FragCoord.xy-u_camP.xy)/u_resolution;
//vec2 st = (gl_FragCoord.xy/u_resolution)+(u_camP.xy/u_resolution);

//u_camP.y = u_camP.y-(u_resolution.y/2);
//vec2 st = (gl_FragCoord.xy-u_camP.xy)/u_resolution;





//vec2 st = (gl_FragCoord.xy+u_camP.xy-(u_resolution.xy/2))/u_resolution;
vec2 st = (gl_FragCoord.xy/u_resolution)+(u_camP.xy/u_resolution);


float pct = 0.0;

    // a. The DISTANCE from the pixel to the center
    pct = distance(st,vec2(0.5));

    // b. The LENGTH of the vector
    //    from the pixel to the center
    // vec2 toCenter = vec2(0.5)-st.xy;
    // pct = length(toCenter);

    // c. The SQUARE ROOT of the vector
    //    from the pixel to the center
    // vec2 tC = vec2(0.5)-st.xy;
    // pct = sqrt(tC.x*tC.x+tC.y*tC.y);

    vec3 color = vec3(pct);

	gl_FragColor = vec4( color, 1.0 );
}
