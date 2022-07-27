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

ExtremeTable.addShapeRecipe( {
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
		d: { id:ItemID.crystal_matrix_ingot }
	});
ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
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
		b: { id: 295 }, c: { id: 391 },
		d: { id: 392 }, e: { id:81 },
		g: { id: 40 }, f: { id: 39 },
		i: { id: 372 }
	});
ExtremeTable.addShapeRecipe( {
	id: ItemID.catalystInfinity,
	count: 1
}, ["acnvsumpf",
	"iglrqde  ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         ",
	"         "], {
		a: { id: ItemID.diamond_lattice },
		c: { id: ItemID.crystal_matrix_ingot },
		n: { id: ItemID.neutron_pile },
		v: { id: ItemID.neutron_nugget },
		s: { id: ItemID.ingotNeutronium },
		i: { id: ItemID.ironsing },
		g: { id: ItemID.goldsing },
		l: { id: ItemID.lapissing },
		r: { id: ItemID.redstonesing },
		q: { id: ItemID.quartzsing },
		d: { id: ItemID.diamondsing },
		e: { id: ItemID.emeraldsing },
		u: { id: ItemID.ultimstew },
		m: { id: ItemID.cosmMeatballs },
		p: { id: ItemID.endestPearl },
		f: { id: ItemID.record_fragment }
	});
ExtremeTable.addShapeRecipe( {
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

ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
	id: ItemID.cosmShovel,
	count: 1
}, ["      aaa",
	"     aaba",
	"      aaa",
	"     c a ",
	"    c    ",
	"   c     ",
	"  c      ",
	" c       ",
	"c        "], {
		a: { id: ItemID.ingotInfinity },
		b: { id: BlockID.infBlock },
		c: { id:ItemID.ingotNeutronium }
	});
ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
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
		b: { id: ItemID.ingotInfinity },
		a: { id: ItemID.ingotNeutronium }
	});
ExtremeTable.addShapeRecipe( {
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

ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
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
ExtremeTable.addShapeRecipe( {
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
