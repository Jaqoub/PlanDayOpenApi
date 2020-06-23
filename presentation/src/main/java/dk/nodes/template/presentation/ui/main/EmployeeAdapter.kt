package dk.nodes.template.presentation.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import dk.nodes.template.models.Employee
import kotlinx.android.synthetic.main.employees_queue.view.*
import timber.log.Timber

class EmployeeAdapter(val context: Context, val recyclerViewQueue: Int ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClickedListener: ((employee: Employee) -> Unit?)? = null

    val employeeList: ArrayList<Employee> = ArrayList()

    override fun getItemCount(): Int {
        return employeeList.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(recyclerViewQueue, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.root.setOnClickListener {
            onItemClickedListener?.invoke(employeeList.get(position))
        }

        var secNames = ""
        holder.firstName?.text = employeeList.get(position).firstName
        holder.lastName?.text = employeeList.get(position).lastName

        employeeList[position].sections?.forEachIndexed { index, element ->
            secNames = secNames + element + "\n"
        }

        holder.section?.text = secNames
    }

    fun addEmployeesList(list: ArrayList<Employee>) {
        employeeList.clear()
        Timber.e(list.toString())
        employeeList.addAll(list)
    }
}

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val firstName = itemView.firstname_info
        val lastName = view.lastname_info
        val section = view.departments_info
        val root = view.employee_item
    }





