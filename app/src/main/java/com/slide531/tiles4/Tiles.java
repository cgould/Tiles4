package com.slide531.tiles4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class Tiles extends AppCompatActivity {

	private LinearLayout tileLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tiles);
		tileLayout = (LinearLayout)findViewById(R.id.tile_layout);
		setupTiles();
	}

	private void setupTiles() {

		for (int i = 0; i < 6; i++) {
			addRow(i);
		}
	}

	private void addRow(int rowNum) {

		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				1.0f);

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT,
				1.0f);

		for (int i = 0; i < 4; i++) {
			View v = new TileView(this);
			v.setLayoutParams(lp);
			ll.addView(v);
		}
		tileLayout.addView(ll, rlp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_tiles, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
