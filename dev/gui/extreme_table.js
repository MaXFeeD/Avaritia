var guiExtremeTable = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: "Extreme crafting table"
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: [],
	elements: {
		scale_0: {
			type: "scale",
			x: 843, y: 252,
			direction: 0,
			bitmap: "arrow",
			scale: 3.2,
			value: 1
		},
		guid: {
			type: "button",
			x: 920, y: 50,
			scale: 1.9,
			bitmap: "guid",
			bitmap2: "guid_press",
			clicker: {
				onClick: function() {
					container.openAs(guid1);
				}
			}
		},
		outputSlot: {
			type: "slot",
			x: 930, y: 250,
			size: 50
		}
	}
});

var guid1 = new UI.StandartWindow({
	standart: {
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		}
	},
	drawing: [],
	elements: {
		image_0: {
			type: "image",
			x: 142, y: 10,
			bitmap: "extreme_nei",
			scale: 2.85
		},
		slot_0: {
			type: "slot",
			x: 173, y: 73,
			size: 52,
			visual: false,
			bitmap: "custom.slot_default",
			needClean: false,
			isTransparentBackground: false
		},
		left: {
			type: "button",
			x: 20, y: 210,
			scale: 3.15,
			bitmap: "guid_left",
			bitmap2: "guid_left_press",
			clicker: {
				onClick: function() {
					container.openAs(extWorckbench);
					container.close(guid1);
				}
			}
		},
		right: {
			type: button,
			x: 880, y: 210,
			scale: 3.15,
			bitmap: "guid_right",
			bitmap2: "guid_right_press"
		},
		result: {
			type: "slot",
			x: 726, y: 266,
			size: 74,
			visual: true
		},
		name: {
			type: "text",
			x: 310, y: 30,
			width: 120,
			height: 16,
			text: "???"
		}
	}
});

var g1 = guid1.getContent();
var r1 = 0, xg1 = 173, yg1 = 73;
for (var i = 0; i < 81; i++) {
	g1.elements["slot" + i] = {
		type: "slot",
		x: xg1,
		y: yg1,
		size: 52,
		visual: true
	};
	xg1 += 52;
	r1++;
	if (r1 == 9) {
		xg1 = 173;
		yg1 += 52;
		r1 = 0;
    }
}

/* let slot = this.container.getSlot("slot");
if(slot.id == 266) return true */

var gc = extWorckbench.getContent();
var ryad = 0, xx = 380, yy = 50;
for (var i = 0; i < 81; i++) {
	gc.elements["inputSlot" + i] = {
		type: "slot",
		x: xx, y: yy,
		size: 50
	};
	xx += 50;
	ryad++;
	if (ryad == 9) {
		xx = 380;
		yy += 50;
		ryad = 0;
	}
}

RecipeTE.registerWorkbench("extWorckbench", {
	GuiScreen: guiExtremeTable,
	rows: 9, cols: 9
});
