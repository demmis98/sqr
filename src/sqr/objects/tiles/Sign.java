package sqr.objects.tiles;

import sqr.All;

public class Sign extends Tile{
	byte num;
	
	public Sign(All all, boolean solid, int x, int y, byte letter) {
		super(all, x, y);
		this.solid = solid;
		num = letter;
		num -= all.getAssets().getFontStart();
		num ++;
		if(num > all.getAssets().font.length -1) {
			num = 0;
		}
		texture = all.getAssets().font[num];
	}
	public Sign(All all, int x, int y, char letter) {
		this(all, false, x, y, (byte) letter);
	}
	public Sign(All all, int x, int y, byte letter) {
		this(all, false, x, y, letter);
	}
	public Sign(All all, int x, int y) {
		this(all, false, x, y, (byte) all.getAssets().getFontStart());
	}
}