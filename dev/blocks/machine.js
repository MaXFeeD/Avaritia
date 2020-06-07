IDRegistry.genBlockID("compressorAv");
Block.createBlockWithRotation("compressorAv", [{
	name: "Compressor neutronium",
	texture: [["colside", 0],["comptop", 0],["colside", 0],["compactive", 0],["colside", 0],["colside", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.compressorAv, 4);
ToolAPI.registerBlockMaterial(BlockID.compressorAv, "stone", 4, true);

IDRegistry.genBlockID("neutCo"); 
Block.createBlockWithRotation("neutCo", [{
	name: "Neutroinium collector",
	texture: [["colside", 0],["coltop", 0],["colside", 0],["colactive", 0],["colside", 0],["colside", 0]],
	inCreative: true
}], "opaque");
Block.setDestroyTime(BlockID.neutCo, 4);
ToolAPI.registerBlockMaterial(BlockID.neutCo, "stone", 4, true);
