let ssize = UI.getScreenHeight()/9.6;
var guiExtremeTable = new UI.StandartWindow({
	standart: {
		header: {
			text: {
				text: Translation.translate("Extreme Crafting Table")
			}
		},
		background: {
			color: android.graphics.Color.parseColor("#c6c6c6")
		},
		inventory: {
			standart: true
		}
	},
	drawing: new Array(),
	elements: {
		scale_0: {
			type: "scale",
			x: UI.getScreenWidth()/2.2 + ssize*9, 
			y: ssize*5,
			direction: 0,
			bitmap: "progress_background",
			scale: ssize/16,
			value: 1
		},
		outputSlot: {
			type: "slot",
			x: UI.getScreenWidth()/2.2 + ssize*10, 
			y: ssize*5,
			size: ssize
		}
	}
});


let content = guiExtremeTable.getContent();
let row = 0, x = UI.getScreenWidth()/2.2, y = UI.getScreenHeight()/13.7;
for (let i = 0; i < 81; i++) {
	content.elements["inputSlot" + i] = {
		type: "slot",
		x: x, y: y,
		size: ssize
	};
	x += ssize;
	row++;
	if (row >= 9) {
		x = UI.getScreenWidth()/2.2;
		y += ssize;
		row = 0;
	}
}

RecipeTE.registerWorkbench("extWorckbench", {
	GuiScreen: guiExtremeTable,
	rows: 9,
	cols: 9
});
