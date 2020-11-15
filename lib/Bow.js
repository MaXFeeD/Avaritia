
LIBRARY({
	name: 'Bow',
	version: 2,
	shared: false,
	api: 'CoreEngine',
	dependencies: [
		'ItemAnimator:1',
		'Timer:2',
		'SoundAPI',
		'Inventory:1'
	]
})

IMPORT('ItemAnimator')
IMPORT('Timer')
IMPORT('SoundAPI', 'Sound')
IMPORT('Inventory')

let ctx = UI.getContext()

ctx.runOnUiThread(new java.lang.Runnable({
	run: function () {
		ctx.getWindow().setFlags(
			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
		)
	}
}))

function newThread(func, name) {
	ctx.runOnUiThread(new java.lang.Runnable({
		run() {
			try {
				func()
			} catch (e) {
				Logger.Log(e, 'Thread ' + name)
				Logger.LogError(e)
			}
		}
	}))
}

function Crosshair() {
	this.visible = false
	this.inGame = false
}
Crosshair.prototype.setUI = function (width, height, aimTexture_base64) {
	let img = aimTexture_base64 || "iVBORw0KGgoAAAANSUhEUgAAACMAAAAjCAYAAAAe2bNZAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsIAAA7CARUoSoAAAABoSURBVFhH7ddBCoAwDADB6q99gc+OCDl4yEKlRYrsXHJ0oaXELSLagDPn05HztT3nEowhxhBjiDFk9AWeymMixpD7AldrQKVaDXpXiK5veEzEGOILTIwhv7rA/sR9whhiDDGGLBTT2gXGSxO6RTVwBgAAAABJRU5ErkJggg=="
	let _str = android.util.Base64.decode(img, 0)
	let decodedBmp = android.graphics.BitmapFactory.decodeByteArray(_str, 0, _str.length)
	let bmp = android.graphics.Bitmap.createBitmap(width || 35, height || 35, android.graphics.Bitmap.Config.ARGB_8888)
	let canvas = new android.graphics.Canvas(bmp)
	canvas.drawBitmap(decodedBmp, 0, 0, null)
	let LayoutParams = android.widget.RelativeLayout.LayoutParams
	let layoutMain = new android.widget.LinearLayout(ctx)
	let params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
	let imgview = new android.widget.ImageView(ctx)
	layoutMain.setOrientation(0)
	layoutMain.setGravity(android.view.Gravity.CENTER)
	layoutMain.setLayoutParams(params)
	layoutMain.addView(imgview)
	imgview.setImageBitmap(bmp)
	let windowAim = new android.widget.PopupWindow(layoutMain, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
	windowAim.setTouchable(false)
	windowAim.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
	this.layout = windowAim
}
Crosshair.prototype.show = function () {
	let this_ = this
	newThread(function () {
		this_.layout.showAtLocation(ctx.getWindow().getDecorView(), android.view.Gravity.LEFT | android.view.Gravity.TOP, 0, 0)
		this_.visible = true
		if (typeof this_._onShow === "function") this_._onShow()
	}, 'Show Crosshair')
}
Crosshair.prototype.hide = function () {
	let this_ = this
	newThread(function () {
		this_.layout.dismiss()
		this_.visible = false
		if (typeof this_._onHide === "function") this_._onHide()
	}, 'Hide Crosshair')
}
Crosshair.prototype.setOnShowListener = function (func) {this._onShow = func}
Crosshair.prototype.setOnHideListener = function (func) {this._onHide = func}




var ArrowData = {
	hurt: [],
	arrows: []
}
function Arrow() {
	let this_ = this
	Callback.addCallback('ProjectileHit', function (projectile, item, target) {
		ArrowData.arrows = ArrowData.arrows.filter(function (ent) {
			if (ent.entity == projectile) {
				if (target.entity != -1 && target.entity != Player.get()) {
					ArrowData.hurt.push({
						entity: target.entity,
						//damage: 80//ent.damage
					})
					if (typeof this_._onHit === "function") this_._onHit()
					Callback.invokeCallback('CustomArrowHit', projectile, item, target)
				}
				return false
			}
			return true
		})
	})
	Callback.addCallback('EntityHurt', function (attacker, victim, damage) {
		var ent = -1
		ArrowData.hurt.hurt = ArrowData.hurt.filter(function (e) {
			if (e.entity == victim && attacker == Player.get()) {
				ent = e
				return false
			}
			if (ent != -1) {
				// alert(damage)
				Game.prevent()
				// alert(damage)
				if (typeof this_._onDamage === "function") this_._onDamage()
				if(ent != Player.get())Entity.damageEntity(ent, damage)
				Callback.invokeCallback('CustomArrowDamage', attacker, victim, damage)
				return true
			}
		})
	})
}
Arrow.prototype.setOnHit = function (func) {this._onHit = func}
Arrow.prototype.setOnDamage = function (func) {this._onDamage = func}
Arrow.prototype.Create = function (params) {
	this.params = params
}
Arrow.prototype.spawn = function () {
	let params = this.params
	let lookAngle = Entity.getLookAngle(Player.get())
	if (lookAngle.yaw == 0) {this.spawn(params); return }
	let c = Entity.getPosition(Player.get()),
		yaw = lookAngle.yaw, pitch = lookAngle.pitch
	v = {
		x: -Math.sin(yaw) * Math.cos(pitch),
		y: Math.sin(pitch),
		z: Math.cos(yaw) * Math.cos(pitch)
	},
	
	lv = Entity.getLookVector(Player.get())
	xp = c.x + (v.x * .5)
	yp = c.y + (v.y * .5)
	zp = c.z + (v.z * .5)
	
	if(lv.x > 0 && lv.z > 0){xp += 1; zp =+ 1}
	else if(lv.x > 0 && lv.z < 0){xp += 1; zp -= 1}
	else if(lv.x < 0 && lv.z > 0){xp -= 1; zp += 1}
	else {xp -= 1; zp -= 1}
	if(lv.y < 0)if(lv.x > 0 || lv.z > 0){yp = c.y - .5; xp += 1; zp += 1}
		else {xp -= 1; yp -= 1}
	entity = Entity.spawn(xp, yp, zp, 80, params.skin)
	Entity.setSkin(entity, params.skin)
	Entity.setVelocity(entity, v.x * params.speed, v.y * params.speed + 0.2, v.z * params.speed)
	this.entity = entity
	ArrowData.arrows.push({entity: this.entity, velocity: v, yaw: yaw, pitch: pitch})
	return entity
}


var BowData = {
	LIST: {}
}
function Bow() {
	this.isCurrent = false
	//this.maxDamage = -1
}

Bow.prototype.Create = function (params) {
	let this_ = this
	IDRegistry.genItemID(params.namedID)
	Item.createItem(params.namedID, params.name, {name: params.baseTexture, meta: params.baseTextureData || 0}, {stack: 1})
	Item.setUseAnimation(params.namedID, 4)
	Item.setToolRender(params.namedID, true)
	Item.setMaxUseDuration(params.namedID, 72000)
	if (params.maxDamage)
		Item.setMaxDamage(params.namedID, params.maxDamage)
	// if (params.ToolAPI) {
	// 	let material = params.ToolAPI
	// 	ToolAPI.addToolMaterial(material.name, {
	// 		durability: material.durability || 256,
	// 		level: material.level || 0,
	// 		efficiency: material.efficiency || 0,
	// 		damage: material.damage || 0,
	// 		enchantability: material.enchantability || 14
	// 	})
	// 	ToolAPI.registerTool(ItemID[params.namedID], material.name)
	// }
	Item.registerNoTargetUseFunction(params.namedID, function (item, ticks) {
		this_.startUse(params, item, ticks)
	})
	Item.registerUsingReleasedFunction(params.namedID, function (item, ticks) {
		this_.endUse(params, item, ticks)
	})
	params.animation.bindAnimator(params.namedID)

	if (typeof this._onCreate === "function") this._onCreate()
	BowData.LIST[params.namedID] = params
}
Bow.prototype.startUse = function (params) {
	/*l1: for (let i = 0; i <= 36; i++) {
		let currID = Player.getInventorySlot(i).id
		*/l2: for (let name in params.arrows) {
			let arrowObj = params.arrows[name]
			/*if (ItemID[arrowObj.arrow.params.namedID] == currID) {
				arrowObj.currSlot = i*/
				this.arrowObj = arrowObj
				this.using = true
				if (typeof this._onStart === "function") this._onStart()
				if (typeof arrowObj.startUseAnimation === 'string')
					params.animation.Start(arrowObj.startUseAnimation)
				/*break l1
			}
		}
	*/}
}
Bow.prototype.endUse = function (params) {
	let arrowObj = this.arrowObj
	if (arrowObj && this.using) {
		//let arrow = Player.getInventorySlot(arrowObj.currSlot)
		//if (Inventory.retrieveItem(arrow.id, arrow.data)) {
			arrowObj.arrow.spawn()
			if (params.sound) {
				let shootSound = new Sound()
				shootSound.setSource(params.sound)
				shootSound.setOnCompletion(function () {
					shootSound.destroy()
				})
				shootSound.play()
			//}
			//if (params.maxDamage)Inventory.damageItem(1)
		}
		params.animation.Stop(true)
		this.using = false
		// if (typeof arrowObj.endUseAnimation === 'string')
		// 	params.animation.Start(arrowObj.endUseAnimation)
		if (typeof this._onStop === "function") this._onStop()
		delete this.arrowObj
	}
}
Bow.prototype.setOnStartUseListener = function (func) {this._onStartUse = func}
Bow.prototype.setOnEndUseListener = function (func) {this._onEndtUse = func}
Bow.prototype.setOnCreateListener = function (func) {this._onCreate = func}

let inGame = false
Callback.addCallback('NativeGuiChanged', function (scr, scrPrev, isPushed) {
	if (scr == 'hud_screen' || scr == 'in_game_play_screen')
		inGame = true; else inGame = false
	updateCrosshair()
})
function updateCrosshair() {
	for (let i in BowData.LIST) {
		let cB = BowData.LIST[i]
		if (cB.crosshair)
			if (inGame && Player.getInventorySlot(Player.getSelectedSlotId()).id == ItemID[i])
				cB.crosshair.show()
			else cB.crosshair.hide()
	}
}
Callback.addCallback("tick", function () {
	updateCrosshair()
})

EXPORT("Bow", Bow)
EXPORT("Arrow", Arrow)
EXPORT("Crosshair", Crosshair)
