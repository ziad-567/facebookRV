package com.example.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contants_app.databinding.ActivityContactsBinding

class ContactsActivity:AppCompatActivity() {
    lateinit var binding: ActivityContactsBinding
    lateinit var name: String
    lateinit var phone: String
    lateinit var description:String
    lateinit var adapter: ContactsAdapter
    val contactList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        onSaveBtnClick()


    }

    private fun initRecyclerView() {
        adapter = ContactsAdapter(contactList)
        binding.rvContacts.adapter = adapter
        adapter.listener = ContactsAdapter.OnContactClickListener { contact->
            navigateToContactDetailsActivity(contact)
        }

        adapter.nameListener = object :ContactsAdapter.OnNameClickListener{
            override fun onClick(contact: Contact) {
                Toast.makeText(this@ContactsActivity,"Name:${contact.name} is clicked!",Toast.LENGTH_LONG)
                    .show()
            }

        }

    }

    private fun navigateToContactDetailsActivity(contact: Contact) {
        val intent = Intent(this, ContactDetailsActivity::class.java)
        intent.putExtra(Constants.CONTACT, contact)
        startActivity(intent)
    }

    private fun onSaveBtnClick() {
        binding.saveBtn.setOnClickListener {
            if (!validateTextFields()) {
                return@setOnClickListener
            }
            name = binding.nameEdt.text?.trim().toString()
            phone = binding.phoneEdt.text?.trim().toString()
            description = binding.descriptionEdt.text?.trim().toString()

            val contact = Contact(name, phone, description)
            contactList.add(contact)
            adapter.notifyItemInserted(contactList.size-1)

        }
    }

    fun validateTextFields():Boolean{
        name = binding.nameEdt.text?.trim().toString()
        phone = binding.phoneEdt.text?.trim().toString()
        binding.nameTil.error = validateName(name)
        binding.phoneTil.error = validatePhone(phone)

        return validateName(name)==null&&validatePhone(phone)==null
    }

    fun validateName(name:String):String?{
        if (name.isEmpty())
            return "Required"
        if (name.length<3)
            return "Name can't be less than 3 characters"
        return null

    }

    fun validatePhone(phone:String):String?{
        if (phone.isEmpty())
            return "Required"
        if (phone.length<11)
            return "Phone can't be less than 11 digits"
        return null
    }
}