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
					Game.tipMessage("Isn't worked");
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

/* let slot = this.container.getSlot("slot");
if(slot.id == 266) return true */

var content = guiExtremeTable.getContent();
var row = 0, x = 380, y = 50;
for (var i = 0; i < 81; i++) {
	content.elements["inputSlot" + i] = {
		type: "slot",
		x: x, y: y,
		size: 50
	};
	x += 50;
	row++;
	if (row >= 9) {
		x = 380;
		y += 50;
		row = 0;
	}
}

RecipeTE.registerWorkbench("extWorckbench", {
	GuiScreen: guiExtremeTable,
	rows: 9, cols: 9
});
