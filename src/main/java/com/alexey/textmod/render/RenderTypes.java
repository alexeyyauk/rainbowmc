package com.alexey.textmod.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import net.minecraft.client.renderer.rendertype.*;

@Environment( EnvType.CLIENT )
public class RenderTypes
{
    private static final RenderType ARMOR_ENTITY_GLINT = RenderType.create(
            "armor_entity_glint",
            RenderSetup.builder( Pipelines.GLINT )
                    .withTexture(
                            "Sampler0",
                            ItemFeatureRenderer.ENCHANTED_GLINT_ARMOR
                                )
                    .setTextureTransform( TextureTransform.ARMOR_ENTITY_GLINT_TEXTURING )
                    .setLayeringTransform( LayeringTransform.VIEW_OFFSET_Z_LAYERING )
                    .createRenderSetup()
                                                                          );
    private static final RenderType GLINT_TRANSLUCENT = RenderType.create(
            "glint_translucent",
            RenderSetup.builder( Pipelines.GLINT )
                    .withTexture(
                            "Sampler0",
                            ItemFeatureRenderer.ENCHANTED_GLINT_ITEM
                                )
                    .setTextureTransform( TextureTransform.GLINT_TEXTURING )
                    .setOutputTarget( OutputTarget.ITEM_ENTITY_TARGET )
                    .createRenderSetup()
                                                                         );
    private static final RenderType GLINT = RenderType.create(
            "glint",
            RenderSetup.builder( Pipelines.GLINT )
                    .withTexture(
                            "Sampler0",
                            ItemFeatureRenderer.ENCHANTED_GLINT_ITEM
                                )
                    .setTextureTransform( TextureTransform.GLINT_TEXTURING )
                    .createRenderSetup()
                                                             );
    private static final RenderType ENTITY_GLINT = RenderType.create(
            "entity_glint",
            RenderSetup.builder( Pipelines.GLINT )
                    .withTexture(
                            "Sampler0",
                            ItemFeatureRenderer.ENCHANTED_GLINT_ITEM
                                )
                    .setTextureTransform( TextureTransform.ENTITY_GLINT_TEXTURING )
                    .createRenderSetup()
                                                                    );

    public static RenderType getArmorEntityGlint()
    {
        return ARMOR_ENTITY_GLINT;
    }
    public static RenderType getEntityGlint()
    {
        return ENTITY_GLINT;
    }
    public static RenderType getGLINT()
    {
        return GLINT;
    }
    public static RenderType getGlintTranslucent()
    {
        return GLINT_TRANSLUCENT;
    }

}
