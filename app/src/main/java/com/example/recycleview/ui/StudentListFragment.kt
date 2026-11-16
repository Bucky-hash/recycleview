package com.example.recycleview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview.databinding.FragmentStudentListBinding
import com.example.recycleview.model.Student
import com.example.recycleview.ui.adapter.StudentListAdapter
import com.example.recycleview.viewmodel.StudentViewModel

class StudentListFragment : Fragment() {
    private var _binding: FragmentStudentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StudentViewModel by activityViewModels()[cite: 11]
    private lateinit var studentAdapter: StudentListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        setupListeners()
    }

    private fun setupRecyclerView() {
        studentAdapter = StudentListAdapter(
            onStudentClick = { student ->
                setFragmentResult("STUDENT_DETAIL_REQ", bundleOf("STUDENT_ID" to student.id))[cite: 11]
            },
            onDeleteClick = { student ->
                // Sửa lại cách gọi hàm xóa (truyền thẳng giá trị vào)
                viewModel.deleteStudent(student.id)
            }
        )

        binding.rvStudents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = studentAdapter
            setHasFixedSize(true)[cite: 11]
        }
    }

    private fun observeViewModel() {
        viewModel.students.observe(viewLifecycleOwner) { list ->[cite: 11]
            studentAdapter.submitList(list)[cite: 12]
            binding.layoutEmpty.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun setupListeners() {
        binding.fabAdd.setOnClickListener {
            val newStudent = Student(
                id = System.currentTimeMillis().toString(),
                mssv = "2011" + (1000..9999).random(),
                name = "Sinh Viên Mới",
                gpa = 3.50,
                major = "Công Nghệ Thông Tin",
                avatarColor = 0xFF7C3AED.toInt()
            )
            viewModel.addStudent(newStudent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null[cite: 12]
    }
}