package com.abcoding.connect.presentation.components

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

// 1. The AGSL Shader for microscopic film grain (noise)
const val NOISE_SHADER = """
    uniform float2 resolution;
    float random(float2 st) {
        return fract(sin(dot(st.xy, float2(12.9898,78.233))) * 43758.5453123);
    }
    half4 main(float2 fragCoord) {
        float noise = random(fragCoord);
        // Monochromatic noise with very low opacity
        return half4(noise, noise, noise, 0.04); 
    }
"""

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppleGradeLiquidGlass(
    modifier: Modifier = Modifier,
    blurRadius: Float = 60f,
    saturationBoost: Float = 1.4f,
    shape: Shape = RoundedCornerShape(33.dp),
    tintColor: Color = Color.White.copy(alpha = 0.15f),
    borderColorStart: Color = Color.White.copy(alpha = 0.5f),
    borderColorEnd: Color = Color.White.copy(alpha = 0.05f),
    content: @Composable BoxScope.() -> Unit
) {
    // Compile the noise shader
    val noiseShader = remember { RuntimeShader(NOISE_SHADER) }

    Box(
        modifier = modifier
            .clip(shape)
            .graphicsLayer {
                // Step A: Create the Blur
                val blurEffect = RenderEffect.createBlurEffect(
                    blurRadius, blurRadius, android.graphics.Shader.TileMode.MIRROR
                )

                // Step B & C: Create the Saturation Boost (Vibrancy) and chain it with the Blur
                val colorMatrix = ColorMatrix().apply { setSaturation(saturationBoost) }
                val colorFilterEffect = RenderEffect.createColorFilterEffect(
                    ColorMatrixColorFilter(colorMatrix), 
                    blurEffect // Chained!
                )

                renderEffect = colorFilterEffect.asComposeRenderEffect()
                
                clip = true
            }
            // Step D: Apply the mathematical noise texture using drawWithContent
            .drawWithContent {
                drawContent()
                noiseShader.setFloatUniform("resolution", size.width, size.height)
                drawRect(
                    brush = androidx.compose.ui.graphics.ShaderBrush(noiseShader),
                    blendMode = BlendMode.Overlay // Blend it naturally into the glass
                )
            }
            // Step E: The standard translucent tint
            .background(tintColor)
            // Step F: The Specular Rim Highlight
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        borderColorStart,
                        borderColorEnd,
                        borderColorStart.copy(alpha = 0.2f)
                    )
                ),
                shape = shape
            )
    ) {
        content()
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LiquidGlassCard(
    modifier: Modifier = Modifier,
    blurRadius: Float = 40f,
    shape: Shape = RoundedCornerShape(33.dp),
    tintColor: Color = Color.White.copy(alpha = 0.15f),
    borderColorStart: Color = Color.White.copy(alpha = 0.4f),
    borderColorEnd: Color = Color.White.copy(alpha = 0.05f),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            // 1. The Blur Effect (Requires API 31+)
            .graphicsLayer {
                renderEffect = RenderEffect
                    .createBlurEffect(blurRadius, blurRadius, android.graphics.Shader.TileMode.MIRROR)
                    .asComposeRenderEffect()
                // Clip strictly to the shape so the blur doesn't bleed outside
                clip = true 
            }
            // 2. The Translucent Tint
            .background(tintColor)
            // 3. The Specular Edge (Light catching the rim)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        borderColorStart,
                        borderColorEnd,
                        borderColorStart.copy(alpha = 0.1f)
                    )
                ),
                shape = shape
            )
    ) {
        content()
    }
}
