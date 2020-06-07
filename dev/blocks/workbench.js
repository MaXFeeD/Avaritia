IDRegistry.genBlockID("compreBlock");
Block.createBlock("compreBlock", [{
	name: "Compresed workbench",
	texture: [["compressed", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.compreBlock, 4);
ToolAPI.registerBlockMaterial(BlockID.compreBlock, "wood", 0, true);

IDRegistry.genBlockID("dcompreBlock");
Block.createBlock("dcompreBlock", [{
	name: "Double compressed workbench",
	texture: [["double_compressed", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.dcompreBlock, 6);
ToolAPI.registerBlockMaterial(BlockID.dcompreBlock, "wood", 2, true);

IDRegistry.genBlockID("extWorckbench");
Block.createBlockWithRotation("extWorckbench", [{
	name: "Extrеme workbench",
	texture: [["crafting", 2],
 ["craftingtop", 0],
 ["craftingside", 0],
  ["craftingside", 0],
   ["craftingside", 0],
    ["craftingside", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.extWorckbench, 4);
ToolAPI.registerBlockMaterial(BlockID.extWorckbench, "stone", 3, true);
