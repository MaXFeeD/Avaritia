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

TileEntity.registerPrototype(BlockID.compressorAv, {
	getGuiScreen: function() {
		return new UI.StandartWindow({
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
					type: "text",
					x: 432,
					y: 157,
					width: 68,
					height: 16,
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
					type: "text",
					x: 883,
					y: 157,
					width: 68,
					height: 16,
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
	},
	defaultValues: {
		id: 0,
		count: 0,
		consumed: 0,
		target: 0,
		result: 0
	},
	updateContainer: function() {
		this.input = this.container.getSlot("slot_0");
		this.result = this.container.getSlot("slot_1");
	},
	updateScreen: function() {
		this.container.validateAll();
		if (this.data.count > 0 && this.data.result != 0) {
			this.container.setText("intext", Translation.translate("Input"));
			this.container.setText("outext", Translation.translate("Output"));
			this.container.setSlot("input", this.data.id, 1, 0);
			this.container.setSlot("output", this.data.result, 1, 0);
			this.container.setText("count", this.data.count + " / " + this.data.target);
		} else {
			this.container.setText("intext", "");
			this.container.setText("outext", "");
			this.container.clearSlot("input");
			this.container.clearSlot("output");
			this.container.setText("count", "");
		}
		if (this.data.target > 0) {
			this.container.setScale("progress", this.data.consumed / CONSUME_TICKS);
			this.container.setScale("singular", this.data.count / this.data.target);
		} else {
			this.container.setScale("progress", 0);
			this.container.setScale("singular", 0);
		}
		this.container.invalidateUI();
	},
	click: function(id, count, data, coords) {
		this.updateScreen();
	},
	tick: function() {
		var isDirty = false;
		this.updateContainer();
		if (this.data.target != 0 && this.data.count >= this.data.target) {
			var result = Compressor.getRecipe(this.data.id);
			if (result) {
				if (this.data.consumed >= CONSUME_TICKS) {
					this.result.id = result.out;
					this.result.count++;
					this.data.count -= this.data.target;
					if (this.data.count == 0) {
						this.data.id = 0;
					}
					this.data.target = 0;
					this.data.result = 0;
					this.data.consumed = 0;
				} else {
					this.data.consumed++;
				}
			} else {
				World.drop(this.x, this.y, this.z, this.data.id, this.data.count);
				this.data.id = this.data.count = this.data.consumed = 0;
			}
			isDirty = true;
		} else {
			var recipe = Compressor.getRecipe(this.input.id);
			if (recipe && (recipe.out == this.result.id || this.result.id == 0)) {
				if (this.data.id == 0 && this.input.id != 0) {
					this.data.id = this.input.id;
					isDirty = true;
				}
				if (this.input.id == this.data.id && this.input.id != 0) {
					this.data.count++;
					this.data.target = recipe.count;
					this.input.count--;
					this.data.result = recipe.out;
					isDirty = true;
				}
			}
		}
		if (this.container.isOpened() && isDirty) {
			this.updateScreen();
		}
	}
});
