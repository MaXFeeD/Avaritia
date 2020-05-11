Callback.addCallback("LevelLoaded", function() {
	Recipes.addShaped({
		id: ItemID.diamond_lattice,
		count: 1, data: 0
	}, ["aoa",
		"oao",
		"aoa"], ["a", 264, 0]);
	Recipes.addShaped({
		id: BlockID.neutroniumBlock,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", ItemID.ingotNeutronium, 0]);
	Recipes.addShaped({
		id: ItemID.neutron_nugget,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", ItemID.neutron_pile, 0]);
	Recipes.addShaped({
		id: ItemID.ingotNeutronium,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", ItemID.neutron_nugget, 0]);
	Recipes.addShaped({
		id: ItemID.crystal_matrix_ingot,
		count: 1, data: 0
	}, ["aba",
		"aba",
		"ooo"], ["a", ItemID.diamond_lattice, 0, "b", 399, 0]);
	Recipes.addShaped({
		id: BlockID.compreBlock,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", 58, 0]);
	Recipes.addShaped({
		id: BlockID.dcompreBlock,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", BlockID.compreBlock, 0]);
	Recipes.addShaped({
		id: BlockID.crystal_matrixAV,
		count: 1, data: 0
	}, ["aaa",
		"aaa",
		"aaa"], ["a", ItemID.crystal_matrix_ingot, 0]);
	Recipes.addShaped({
		id: BlockID.extWorckbench,
		count: 1, data: 0
	}, ["aaa",
		"aba",
		"aaa"], ["a", ItemID.crystal_matrix_ingot, 0, "b", BlockID.dcompreBlock, 0]);
});

RecipeTE.addShapeRecipe("extWorckbench", {
	id: BlockID.neutCo,
	count: 1
}, ["aabbbbbaa",
	"a bbbbb a",
	"a  ccc  a",
	"d ccccc d",
	"a ccdcc a",
	"d ccccc d",
	"a  ccc  a",
	"a       a",
	"aaadadaaa"], {
		a: { id: 42 }, b: { id: 155 }, c: { id: 152 },
		d:{ id:ItemID.crystal_matrix_ingot }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.endestPearl,
	count: 1
}, ["   aaa   ",
	" aabbbaa ",
	" abbbbba ",
	"abbbcbbba",
	"abbcscbba",
	"abbbcbbba",
	" abbbbba ",
	" aabbbaa ",
	"   aaa   "], {
		a: { id: 121 }, b: { id: 368 },
		c: { id:ItemID.ingotNeutronium },
		s: { id:399 }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: BlockID.compressorAv,
	count: 1
}, ["aaabbbaaa",
	"c n   n c",
	"a n   n a",
	"c n   n c",
	"rnn g nnr",
	"c n   n c",
	"a n   n a",
	"c n   n c",
	"aaacacaaa"], {
		a: { id: 42 }, b: { id: 410 },
		c: { id: ItemID.crystal_matrix_ingot },
		n: { id: ItemID.ingotNeutronium },
		r: { id: 152 },
		g: { id: BlockID.neutroniumBlock }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmMeatballs,
	count: 2
}, ["abbccddee",
	"ff       ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "], {
		a: { id: ItemID.neutron_pile },
		b: { id: 363 }, c: { id: 365 },
		d: { id: 319 }, f: { id: 349 },
		e: { id:411 }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.ultimstew,
	count: 1
}, ["abbccddee",
	"ggffii   ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "], {
		a: { id: ItemID.neutron_pile },
		b: { id: 256 }, c: { id: 391 },
		d: { id: 392 }, e: { id:81 },
		g: { id: 40 }, f: { id: 39 },
		i: { id: 372 }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.catalystInfinity,
	count: 1
}, ["aiglrqube",
	"f        ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "], {
		a: { id: 133 },
		i: { id: ItemID.ironsing },
		g: { id: ItemID.goldsing },
		l: { id: ItemID.lapissing },
		r: { id: ItemID.redstonesing },
		q: { id: ItemID.quartzsing },
		u: { id: ItemID.ultimstew },
		b: { id: ItemID.cosmMeatballs },
		e: { id: ItemID.endestPearl },
		f: { id: ItemID.record_fragment }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.ingotInfinity,
	count: 1
}, ["aaaaaaaaa",
	"abccbccba",
	"acbbcbbca",
	"abccbccba",
	"aaaaaaaaa",
	"         ",
	"         ",
	"         ",
	"         "],{
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.crystal_matrix_ingot },
		c: { id: ItemID.catalystInfinity }
	});

RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmSword,
	count: 1
}, ["       aa",
	"      aaa",
	"     aaa ",
	"    aaa  ",
	" b aaa   ",
	"  baa    ",
	"  cb     ",
	" c  b    ",
	"d        "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: ItemID.crystal_matrix_ingot },
		c: { id: ItemID.ingotNeutronium },
		d: { id:ItemID.catalystInfinity }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmAxe,
	count: 1
}, [" a       ",
	"aaaaa    ",
	"aaaa     ",
	" ab      ",
	"  b      ",
	"  b      ",
	"  b      ",
	"  b      ",
	"  b      "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: ItemID.ingotNeutronium }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmShovel,
	count: 1
}, ["      aaa",
	"     aaba",
	"      aaa",
	"     c  a",
	"    c    ",
	"   c     ",
	"  c      ",
	" c       ",
	"c        "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: BlockID.infBlock },
		c: { id:ItemID.ingotNeutronium }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
    id: ItemID.infbow,
    count: 1
}, ["   aa    ",
	"  a b    ",
	" a  b    ",
	"a   b    ",
	"c   b    ",
	"a   b    ",
	" a  b    ",
	"  a b    ",
	"   aa    "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: 35 },
		c: { id: BlockID.crystal_matrixAV }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmPickaxe,
	count: 1
}, [" aaaaaaa ",
	"aaaabaaaa",
	"aa  c  aa",
	"    c    ",
	"    c    ",
	"    c    ",
	"    c    ",
	"    c    ",
	"    c    "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: BlockID.crystal_matrixAV },
		c: { id:ItemID.ingotNeutronium }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.cosmHoe,
	count: 1
}, ["     a   ",
	" bbbbbb  ",
	"bbbbbbb  ",
	"b    bb  ",
	"     a   ",
	"     a   ",
	"     a   ",
	"     a   ",
	"     a   "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: ItemID.ingotNeutronium }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.skull_sword,
	count: 1
}, ["       ab",
	"      aba",
	"     aba ",
	"    aba  ",
	" c aba   ",
	"  cba    ",
	"  wc     ",
	" w  c    ",
	"s        "], {
		a: { id: ItemID.crystal_matrix_ingot },
		b: { id:377 }, c: { id:352 },
		w: { id: 17 }, s: { id:399 }
	});

RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.inf_helmet,
	count: 1
}, ["  aaaaa  ",
	" abbbbba ",
	" a cbc a ",
	" abbbbba ",
	" abbbbba ",
	" ab b ba ",
	"         ",
	"         ",
	"         "], {
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.ingotInfinity },
		c: { id: ItemID.catalystInfinity }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.inf_chestplate,
	count: 1
}, [" aa   aa ",
	"aaa   aaa",
	"aaa   aaa",
	" abbbbba ",
	" abbcbba ",
	" abbbbba ",
	" abbbbba ",
	" abbbbba ",
	"  aaaaa  "], {
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.ingotInfinity },
		c: { id: BlockID.crystal_matrixAV }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.inf_leggings,
	count: 1
}, ["aaaaaaaaa",
	"abbbcbbba",
	"abaacaaba",
	"aba   aba",
	"aea   aea",
	"aba   aba",
	"aba   aba",
	"aba   aba",
	"aba   aba"], {
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.ingotInfinity },
		c: { id: ItemID.catalystInfinity },
		e: { id:BlockID.crystal_matrixAV }
	});
RecipeTE.addShapeRecipe("extWorckbench", {
	id: ItemID.inf_boots,
	count: 1
}, [" aaa aaa ",
	" aba aba ",
	" aba aba ",
	"aaba abaa",
	"abba abba",
	"aaaa aaaa",
	"         ",
	"         ",
	"         "], {
		a: { id: ItemID.ingotNeutronium },
		b: { id: ItemID.ingotInfinity }
	});
