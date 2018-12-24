package com.vandendaelen.k9.entities.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;

public class ModelRendererGlow extends ModelRenderer {
	
	public ModelRendererGlow(ModelBase model, String boxNameIn) {
		super(model, boxNameIn);
	}
	
	public ModelRendererGlow(ModelBase model) {
		super(model);
	}
	
	public ModelRendererGlow(ModelBase model, int texOffX, int texOffY) {
		super(model, texOffX, texOffY);
	}
	
	@Override
	public void render(float scale) {
		GlStateManager.pushMatrix();
		int i = 15728880;
		int j = i % 65536;
		int k = i / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0F, k / 1.0F);
		super.render(scale);
		GlStateManager.popMatrix();
	}
}
