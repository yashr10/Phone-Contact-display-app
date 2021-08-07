package com.example.unicodetask2

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class Fragment1 : Fragment() , DatePickerDialog.OnDateSetListener {

    private lateinit var nameF: EditText
    private lateinit var birtDateF: EditText
    private lateinit var phoneNumberF: EditText
    private lateinit var dBase: AppDataBase
    private lateinit var clear: Button
    private lateinit var add: Button
    private lateinit var information: Button
    private lateinit var contact: Button
    private lateinit var fab: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_1, container, false)



        nameF = view.findViewById(R.id.name)
        birtDateF = view.findViewById(R.id.birthdate)
        phoneNumberF = view.findViewById(R.id.phonenumber)
        add = view.findViewById(R.id.confirm)
        clear = view.findViewById(R.id.clear)
        information = view.findViewById(R.id.information)
        fab = view.findViewById(R.id.floatingActionButton)

        /*if (savedInstanceState != null){

            val a = savedInstanceState.getString("name")!!
            nameF.text = a as Editable


        }*/



        dBase = activity?.let { AppDataBase(it) }!!

        add.setOnClickListener() {

            val name = nameF.text.toString()
            val birthDate = birtDateF.text.toString()
            val phoneNumber = phoneNumberF.text.toString()

            if (name.isEmpty() || birthDate.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(activity, "Please enter all the data..", Toast.LENGTH_SHORT).show()
            } else {

                dBase.addDetails(name, birthDate, phoneNumber)

                Toast.makeText(activity, "Data has been added.", Toast.LENGTH_SHORT).show()

                nameF.setText("")
                phoneNumberF.setText("")
                birtDateF.setText("")
            }

        }
        birtDateF.setOnClickListener() {
            showDatePickerDialog()
        }
        clear.setOnClickListener() {
            nameF.setText("")
            phoneNumberF.setText("")
            birtDateF.setText("")
        }

        information.setOnClickListener() {

            val fragment2 = Fragment2()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment2)
                ?.addToBackStack("fragment1")?.commit()

        }


        phoneNumberF.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validateMobile(phoneNumberF.text.toString())) {
                    add.isEnabled = true
                } else {

                    add.isEnabled = false
                    phoneNumberF.setError("Invalid Mobile Number")
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }


        })

        val hasReadContactPermission =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)

        if (hasReadContactPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_CONTACTS),
                1
            )
        }

        val contentResolver = requireActivity().contentResolver



        fab.setOnClickListener() { view ->
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val contactList = ArrayList<ContactDetails>()
                val contacts = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
                )

                while (contacts!!.moveToNext()) {

                    val name =
                        contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val phone =
                        contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                    val obj = ContactDetails()
                    obj.name = name
                    obj.phone = phone

                    /*val photo_uri = contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)

                    val source  = ImageDecoder.createSource(contentResolver , Uri.parse(photo_uri.toString()))

                    val bitmap = ImageDecoder.decodeBitmap(source)

                    obj.image = bitmap*/
                    contactList.add(obj)

                }

                val contactFragment = ContactFragment()
                val bundle = Bundle()
                bundle.putSerializable("contactlist", contactList)

                val transaction = activity?.supportFragmentManager?.beginTransaction()

                contactFragment.arguments = bundle
                transaction?.replace(R.id.fragment_container, contactFragment)
                transaction?.addToBackStack(" fragment1")
                transaction?.commit()

            } else {
                Snackbar.make(
                    view,
                    "Please grant access to your Contacts",
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction("Grant Access") {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                requireActivity(),
                                Manifest.permission.READ_CONTACTS
                            )
                        ) {

                            ActivityCompat.requestPermissions(
                                requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS),
                                1
                            )
                        } else {
                            // The user has permanently denied the permission, take them direct to the settings

                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri = Uri.fromParts("package", requireActivity().packageName, null)

                            intent.data = uri
                            this.startActivity(intent)
                        }

                    }.show()
            }


        }



        return view
    }

    private fun validateMobile(input: String): Boolean {
        val p: Pattern = Pattern.compile("[7-9][0-9]{9}")

        val m: Matcher = p.matcher(input)
        return m.matches()

    }


    fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            this,
            Calendar.getInstance()[Calendar.YEAR],
            Calendar.getInstance()[Calendar.MONTH],
            Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        val date = "$dayOfMonth/${month + 1}/$year"
        birtDateF.setText(date)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (nameF.text.isNotEmpty() || birtDateF.text.isNotEmpty() || phoneNumberF.text.isNotEmpty()) {

            outState.putString("name", nameF.text.toString())
            outState.putString("DOB", birtDateF.text.toString())
            outState.putString("phone", phoneNumberF.text.toString())


        }

    }
}

