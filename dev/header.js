/*
 ▄▄▄          ██▒   █▓    ▄▄▄          ██▀███      ██▓   ▄▄▄█████▓    ██▓    ▄▄▄      
▒████▄       ▓██░   █▒   ▒████▄       ▓██ ▒ ██▒   ▓██▒   ▓  ██▒ ▓▒   ▓██▒   ▒████▄    
▒██  ▀█▄      ▓██  █▒░   ▒██  ▀█▄     ▓██ ░▄█ ▒   ▒██▒   ▒ ▓██░ ▒░   ▒██▒   ▒██  ▀█▄  
░██▄▄▄▄██      ▒██ █░░   ░██▄▄▄▄██    ▒██▀▀█▄     ░██░   ░ ▓██▓ ░    ░██░   ░██▄▄▄▄██ 
 ▓█   ▓██▒      ▒▀█░      ▓█   ▓██▒   ░██▓ ▒██▒   ░██░     ▒██▒ ░    ░██░    ▓█   ▓██▒
 ▒▒   ▓▒█░      ░ ▐░      ▒▒   ▓▒█░   ░ ▒▓ ░▒▓░   ░▓       ▒ ░░      ░▓      ▒▒   ▓▒█░
  ▒   ▒▒ ░      ░ ░░       ▒   ▒▒ ░     ░▒ ░ ▒░    ▒ ░       ░        ▒ ░     ▒   ▒▒ ░
  ░   ▒           ░░       ░   ▒        ░░   ░     ▒ ░     ░          ▒ ░     ░   ▒   
      ░  ░         ░           ░  ░      ░         ░                  ░           ░  ░
                  ░                                                                   
*/

IMPORT("RecipeTileEntityLib");
IMPORT("ToolType");
IMPORT("SoundAPI");
IMPORT("Bow");

var isHorizon = getCoreAPILevel() > 8;

Item.setRequiresIconOverride = ModAPI.requireGlobal("Item.setRequiresIconOverride");
if (!isHorizon) Game.getGameMode = ModAPI.requireGlobal("Level.getGameMode");
var EntityDataRegistry = ModAPI.requireGlobal("EntityDataRegistry");

var ArmorTick = {
	attachTo: function(arg) {
		if (!arg.tick || arg.id == undefined || arg.type == undefined) return;
		Callback.addCallback("tick", function() {
			if (Player.getArmorSlot(arg.type).id == arg.id) arg.tick();
		});
	}
};

var SaplingRenderer = {
	setOnBlock: function(id, offset) {
		var shape = new ICRender.CollisionShape();
		BlockRenderer.setCustomCollisionShape(Block.getNumericId(id), -1, shape);
		BlockRenderer.addRenderCallback(id, function(api, coords, block) {
			if (offset != undefined && offset != 0) {
				for (var i = 0; i < 1 / offset; i += offset) {
					api.renderBoxId(coords.x, coords.y, coords.z, 0 + i, 0.01, 0 + i, offset + i, 0.99, x + i, id, block.data);
					api.renderBoxId(coords.x, coords.y, coords.z, (1 - offset) - i, 0.01, 0 + i, 1 - i, 0.99, offset + i, id, block.data);
				}
			} else {
				api.renderBoxId(coords.x, coords.y, coords.z, 0.4999, 0.01, 0, 0.5, 0.99, 1, id, block.data);
				api.renderBoxId(coords.x, coords.y, coords.z, 0, 0.01, 0.4999, 1, 0.99, 0.5, id, block.data);
			}
		});
		BlockRenderer.enableCustomRender(id);
	}
};

function makeReplaceable(item, id, replacement) {
	if (item == undefined || typeof item != "object" || typeof item.id != "number") return;
	if (item.id == id) Player.setCarriedItem(replacement, item.count, item.data, item.extra);
	else if (item.id == replacement) Player.setCarriedItem(id, item.count, item.data, item.extra);
}

function addNamedCallback(action, current) {
	Callback.addCallback(current, function() {
		var arguments = Array.prototype.slice.call(arguments);
		(arguments.unshift(current), action.apply(Callback, arguments));
	});
}

function makeSimplifiedCallback(action) {
	var arguments = Array.prototype.slice.call(arguments);
	if (arguments.length == 0) return Logger.Log("can't create makeSimplifiedCallback without action & names", "ERROR");
	if (arguments.length == 1) return Logger.Log("no names specified for makeSimplifiedCallback", "ERROR");
	arguments.shift(); // removing action from callback names, it attached into function
	arguments.forEach(function(current) { addNamedCallback(action, current); });
}
