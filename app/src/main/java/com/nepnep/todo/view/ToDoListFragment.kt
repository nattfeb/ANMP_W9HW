package com.nepnep.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.nepnep.todo.R
import com.nepnep.todo.viewmodel.ListTodoViewModel
import kotlinx.android.synthetic.main.fragment_to_do_list.*

class ToDoListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    private lateinit var viewModel: ListTodoViewModel
    private val todoListAdapter  = TodoListAdapter(arrayListOf(), { item -> viewModel.doneTask(item.uuid) })


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()
        recViewTodo.layoutManager = LinearLayoutManager(context)
        recViewTodo.adapter = todoListAdapter

        fabAddTodo.setOnClickListener {
            val action = ToDoListFragmentDirections.actionToDoListFragmentToCreateToDoFragment()
            Navigation.findNavController(it).navigate(action)
        }

        fun observeViewModel() {
            viewModel.todoLD.observe(viewLifecycleOwner, Observer {
                todoListAdapter.updateTodoList(it)
                if(it.isEmpty()) {
                    txtEmpty.visibility = View.VISIBLE
                } else {
                    txtEmpty.visibility = View.GONE
                }
            })
        }

        observeViewModel()
    }

}