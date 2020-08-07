var compressorGUI = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: Translation.translate("Compressor")
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	elements: {
		intext: {
			type: Translation.translate("text"),
			x: 432,
			y: 157,
			width: 68,
			height: 16,
			text: "Input",
			font: {
				alignment: 1
			}
		},
		input: {
			type: "slot",
			x: 402,
			y: 192,
			size: 68,
			visual: true,
			needClean: true,
			isTransparentBackground: true,
			bitmap: "transparent"
		},
		slot_0: {
			type: "slot",
			x: 490,
			y: 192,
			size: 68,
			visual: false,
			needClean: false,
			isTransparentBackground: false
		},
		progress: {
			type: "scale",
			x: 574,
			y: 200,
			pixelate: true,
			bitmap: "progress_singular",
			background: "progress_background",
			scale: 3.6,
			value: 0
		},
		singular: {
			type: "scale",
			x: 667,
			y: 190,
			direction: 1,
			pixelate: true,
			bitmap: "singular",
			background: "singular_background",
			scale: 4.6,
			value: 0
		},
		slot_1: {
			type: "slot",
			x: 760,
			y: 192,
			size: 68,
			visual: false,
			needClean: false,
			isTransparentBackground: false
		},
		count: {
			type: "text",
			x: 660,
			y: 272,
			width: 120,
			height: 16,
			font: {
				alignment: 1
			}
		},
		outext: {
			type: Translation.translate("text"),
			x: 898,
			y: 157,
			width: 68,
			height: 16,
			text: "Output",
			font: {
				alignment: 1
			}
		},
		output: {
			type: "slot",
			x: 858,
			y: 192,
			size: 68,
			visual: true,
			needClean: true,
			isTransparentBackground: true,
			bitmap: "transparent"
		}
	}
});

var Compressor = {
	recipes: new Object(),
	addRecipe: function(key, recipe) {
		if (this.recipes.hasOwnProperty(key)) {
			Logger.Log("New recipe " + key + " will be override old one", "Warning")
		}
		this.recipes[key] = recipe;
	},
	getRecipe: function(key) {
		if (this.recipes.hasOwnProperty(key)) {
			return this.recipes[key];
		}
		return null;
	}
};

Compressor.addRecipe(42, {
	count: 400,
	out: ItemID.ironsing
});
Compressor.addRecipe(41, {
	count: 200,
	out: ItemID.goldsing
});
Compressor.addRecipe(155, {
	count: 200,
	out: ItemID.quartzsing
});
Compressor.addRecipe(152, {
	count: 500,
	out: ItemID.redstonesing
});
Compressor.addRecipe(22, {
	count: 200,
	out: ItemID.lapissing
});

// TODO: Diamonds (x300)
// TODO: Emeralds (x200)

var CONSUME_TICKS = 100;

TileEntity.registerPrototype(BlockID.compressorAv,{
	defaultValues: {
		count: 0,
		progress: 0,
		target: 0,
		consumed: 0,
		id: 0,
		result: null
	},
	tick: function() {
		var slot0 = this.container.getSlot("slot_0");
		var slot1 = this.container.getSlot("slot_1");
		var recipe = Compressor.getRecipe(slot0.id);
		if (recipe) {
			if (this.data.id == 0 && slot0.id != 0) {
				this.data.id = slot0.id;
			}
			if (slot0.id == this.data.id && slot0.id != 0) {
				this.data.count += 1;
				this.data.target = recipe.count; 
				this.data.progress += 1 / this.data.target;
				slot0.count--;
				this.data.result = recipe.out;
				this.container.setSlot("output", this.data.result, 1, 0);
			}
		}
		if (this.data.progress >= 1) {
			var result = Compressor.getRecipe(this.data.id);
			if (result) {
				if (this.data.consumed >= CONSUME_TICKS) {
					slot1.id = result.out;
					slot1.count++;
					this.data.count -= this.data.target;
					if (this.data.count == 0) {
						this.data.id = 0;
					}
					this.data.consumed = 0;
					this.data.progress--;
				} else {
					this.data.consumed++;
				}
			}
		}
		this.container.setSlot("input", this.data.id, 1, 0);
		this.container.setText("count", this.data.count + " / " + this.data.target);
		if (this.data.target > 0) {
			this.container.setScale("progress", this.data.consumed / CONSUME_TICKS);
			this.container.setScale("singular", this.data.count / this.data.target);
		} else {
			this.container.setScale("progress", 0);
			this.container.setScale("singular", 0);
		}
		this.container.validateAll();
	},
	getGuiScreen: function() {
		return compressorGUI;
	}
});
