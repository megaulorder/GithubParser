package com.example.githubparser

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.githubparser.ui.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30])
class UnitTest {

	private lateinit var activity: MainActivity

	@Before
	fun setMainActivity() {
		activity = Robolectric.buildActivity(MainActivity::class.java).create().visible().get()
	}

	@Test
	@Throws(Exception::class)
	fun checkToolbarTitle() {
		val toolbar: Toolbar = activity.findViewById(R.id.toolbar)
		assertEquals("Repositories", toolbar.title.toString())
	}

	@Test
	@Throws(Exception::class)
	fun checkBottomNavigation() {
		val navigation: BottomNavigationView = activity.findViewById(R.id.bottomNavigation)

		val mockedListener: OnItemSelectedListener = mock(OnItemSelectedListener::class.java)
		navigation.setOnItemSelectedListener(mockedListener)
		`when`(mockedListener.onNavigationItemSelected(any(MenuItem::class.java))).thenReturn(true)

		val profile = R.id.profile

		// verify the item is not selected
		assertFalse(navigation.menu.findItem(profile).isChecked)

		// select item
		navigation.selectedItemId = profile

		// verify that listener has been notified of the tap
		verify(
			mockedListener, times(1)
		).onNavigationItemSelected(navigation.menu.findItem(profile));

		// verify the item is selected
		assertTrue(navigation.menu.findItem(profile).isChecked)

		// select the same item
		navigation.selectedItemId = profile

		// verify that listener has been notified of the click
		verify(mockedListener, times(2))
			.onNavigationItemSelected(navigation.menu.findItem(profile))

		// verify the item is still selected
		assertTrue(navigation.menu.findItem(profile).isChecked);
	}
}
