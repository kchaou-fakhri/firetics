package com.eljem.firetics

import androidx.appcompat.app.AppCompatActivity
import com.eljem.firetics.view.drawer.DrawerAdapter
import android.graphics.drawable.Drawable
import com.yarolegovich.slidingrootnav.SlidingRootNav
import android.os.Bundle
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.appcompat.widget.Toolbar
import com.eljem.firetics.databinding.ActivityMainBinding
import com.eljem.firetics.view.dataUsage.DataUsageFragment
import com.eljem.firetics.view.drawer.DrawerItem
import com.eljem.firetics.view.drawer.SimpleItem
import com.eljem.firetics.view.drawer.SpaceItem
import java.util.*

class MainActivity : AppCompatActivity(), DrawerAdapter.OnItemSelectedListener {

    private lateinit var screenTitles: Array<String>
    private lateinit var screenIcons: Array<Drawable?>
    private var slidingRootNav: SlidingRootNav? = null
    var binding: ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        slidingRootNav = SlidingRootNavBuilder(this)
            .withDragDistance(180)
            .withRootViewScale(0.75f)
            .withRootViewElevation(25)
            .withToolbarMenuToggle(toolbar)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withMenuLayout(R.layout.drawer_layout)
            .withSavedState(savedInstanceState)
            .inject()
        screenIcons = loadScreenIcons()
        screenTitles = loadScreenTitles()
        val adapter = DrawerAdapter(
            Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                SpaceItem(48),
                createItemFor(POS_LOGOUT)
            )
        )
        adapter.setListener(this)
        val list = findViewById<RecyclerView>(R.id.drawer_list)
        list.isNestedScrollingEnabled = false
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setSelected(POS_DASHBOARD)
    }

    private fun createItemFor(position: Int): DrawerItem<*> {
        return SimpleItem(
            screenIcons[position],
            screenTitles[position]
        )
            .withIconTint(color(R.color.primary_color))
            .withTextTint(color(R.color.text_color))
            .withSelectedIconTint(color(R.color.secand_color))
            .withSelectedTextTint(color(R.color.secand_color))
    }

    private fun createltemfor(position: Int): DrawerItem<*> {
        return SimpleItem(
            screenIcons[position],
            screenTitles[position]
        )
            .withIconTint(color(R.color.white))
    }

    @ColorInt
    private fun color(@ColorRes res: Int): Int {
        return ContextCompat.getColor(this, res)
    }

    private fun loadScreenTitles(): Array<String> {
        return resources.getStringArray(R.array.id_activityScreenTitles)
    }

    private fun loadScreenIcons(): Array<Drawable?> {
        val ta = resources.obtainTypedArray(R.array.id_activityScreenIcons)
        val icons = arrayOfNulls<Drawable>(ta.length())
        for (i in 0 until ta.length()) {
            val id = ta.getResourceId(i, 0)
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id)
            }
        }
        ta.recycle()
        return icons
    }

    override fun onItemSelected(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        if (position == POS_DASHBOARD) {
            val dataUsageFragment = DataUsageFragment()
            transaction.replace(R.id.container, dataUsageFragment)
        } else if (position == POS_ACCOUNT) {
            val newUserFragment = NewUserFragment()
            transaction.replace(R.id.container, newUserFragment)
        } else if (position == POS_CLOSE) {

        }
        slidingRootNav!!.closeMenu()
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {
        super.onPointerCaptureChanged(hasCapture)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        private const val POS_CLOSE = 0
        private const val POS_DASHBOARD = 1
        private const val POS_ACCOUNT = 2
        private const val POS_MESSAGES = 3
        private const val POS_CART = 4
        private const val POS_LOGOUT = 5
    }
}