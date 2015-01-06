package fr.enoent.staggeredgridlayoutmanagertest;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
	private Adapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adapter = new Adapter();
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(adapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.activate_first:
				adapter.activateItem(1, true);
				return true;

			case R.id.activate_sec:
				adapter.activateItem(2, true);
				return true;

			case R.id.activate_third:
				adapter.activateItem(3, true);
				return true;

			case R.id.deactivate_first:
				adapter.activateItem(1, false);
				return true;

			case R.id.deactivate_sec:
				adapter.activateItem(2, false);
				return true;

			case R.id.deactivate_third:
				adapter.activateItem(3, false);
				return true;

			case R.id.move_first_top:
				adapter.moveItem(1, 0);
				return true;

			case R.id.move_sec_top:
				adapter.moveItem(2, 0);
				return true;

			case R.id.move_third_top:
				adapter.moveItem(3, 0);
				return true;

			case R.id.move_first_end:
				adapter.moveItem(1, 2);
				return true;

			case R.id.move_sec_end:
				adapter.moveItem(2, 2);
				return true;

			case R.id.move_third_end:
				adapter.moveItem(3, 2);
				return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
