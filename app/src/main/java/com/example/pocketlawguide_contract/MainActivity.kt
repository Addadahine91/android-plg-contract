package com.example.pocketlawguide_contract

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.ListView
import android.view.MenuItem
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import com.example.pocketlawguide_contract.R.id
import com.example.pocketlawguide_contract.R.id.*

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* setSupportActionBar(toolbar) */

        listView = findViewById<ListView>(recipe_list_view)
        val recipeList = Case.getRecipesFromFile("recipes.json", this)
        val listItems = arrayOfNulls<String>(recipeList.size)
        for (i in 0 until recipeList.size) {
            val recipe = recipeList[i]
            listItems[i] = recipe.name
        }

        val adapter = RecipeAdapter(this, recipeList)
        listView.adapter = adapter

        val context = this
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedRecipe = recipeList[position]
            val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)
            startActivity(detailIntent)
        }

        tabLayout = findViewById<TabLayout>(id.tabLayout)
        viewPager = findViewById<ViewPager>(id.viewPager)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Cases"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Legislation"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Glossary"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val tabAdapter = MyPagerAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = tabAdapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position

                if (viewPager!!.currentItem == 0) {

                    listView = findViewById(recipe_list_view)
                    val recipeList = Case.getRecipesFromFile("recipes.json", context)
                    val listItems = arrayOfNulls<String>(recipeList.size)
                    for (i in 0 until recipeList.size) {
                        val recipe = recipeList[i]
                        listItems[i] = recipe.name
                    }

                    val adapter = RecipeAdapter(context, recipeList)
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()

                    val context = context
                    listView.setOnItemClickListener { _, _, position, _ ->
                        val selectedRecipe = recipeList[position]
                        val detailIntent = RecipeDetailActivity.newIntent(context, selectedRecipe)
                        startActivity(detailIntent)
                    }

                } else if (viewPager!!.currentItem == 1) {

                    listView = findViewById(recipe_list_view)
                    val legislationList = Leg.getRecipesFromFile("legislation.json", context)
                    val legislationItems = arrayOfNulls<String>(legislationList.size)
                    for (i in 0 until legislationList.size) {
                        val legislation = legislationList[i]
                        legislationItems[i] = legislation.name
                    }

                    val adapter = LegislationAdapter(context, legislationList)
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()

                    val context = context
                    listView.setOnItemClickListener { _, _, position, _ ->
                        val selectedRecipe = legislationList[position]
                        val detailIntent = LegDetailActivity.newIntent(context, selectedRecipe)
                        startActivity(detailIntent)
                    }

                } else {

                    listView = findViewById(recipe_list_view)
                    val recipeList = Gloss.getRecipesFromFile("glossary.json", context)
                    val listItems = arrayOfNulls<String>(recipeList.size)
                    for (i in 0 until recipeList.size) {
                        val recipe = recipeList[i]
                        listItems[i] = recipe.name
                    }

                    val adapter = GlossaryAdapter(context, recipeList)
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()

                    val context = context
                    listView.setOnItemClickListener { _, _, position, _ ->
                        val selectedRecipe = recipeList[position]
                        val detailIntent = GlossaryDetailActivity.newIntent(context, selectedRecipe)
                        startActivity(detailIntent)
                    }

                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

//        val searchView: SearchView
//
//        val inflater = menuInflater
//        inflater.inflate(R.menu.menu_main, menu)
//        val searchItem = menu!!.findItem(action_search)
//        searchView = searchItem.actionView as SearchView
//        searchView.isSubmitButtonEnabled = true
//        searchView.queryHint = "Search either - MindOrks, GetMeAnApp, BestContentApp, Hackerspace"
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(newText: String): Boolean {
//
//                listView = findViewById<ListView>(recipe_list_view)
//                val recipeList = Case.getRecipesFromFile("recipes.json", this)
//                val listItems = arrayOfNulls<String>(recipeList.size)
//                for (i in 0 until recipeList.size) {
//                    val recipe = recipeList[i]
//                    listItems[i] = recipe.name
//                }
//
//                val adapter = RecipeAdapter(this, recipeList)
//                listView.adapter = adapter
//            }
//
//            override fun onQueryTextSubmit(query: String): Boolean {
//                return true
//            }
//        })
//        return super.onCreateOptionsMenu(menu)

       // menuInflater.inflate(R.menu.menu_main, menu)
//
//        val searchItem =  menu.findItem(action_search)
//        // Optional: if you want to expand SearchView from icon to edittext view
//        searchItem.expandActionView()
//
//        val searchView = searchItem.actionView as SearchView

       // return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {

            else -> super.onOptionsItemSelected(item)
        }
    }
}
