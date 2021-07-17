package com.example.sampleproject_1.waterTracker


import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.sampleproject_1.R
import com.example.sampleproject_1.WaterReminder.Utils.AppUtils
import com.example.sampleproject_1.weightTracker.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import java.text.DecimalFormat
import java.util.*

class UserDetailsPageWaterTracker : AppCompatActivity() {

    private lateinit var genderLinearLayout: RelativeLayout
    private lateinit var weightLinearLayout: RelativeLayout

    private lateinit var coordinatorLayoutWaterTracker: CoordinatorLayout

    private lateinit var genderOption: TextView
    private lateinit var userWeight: TextView

    private lateinit var dialog: Dialog

    private lateinit var continueButton: TextView

    private val sharedPreferences: SharedPreferences by inject()

    private var kgOptionISChecked = false
    private var lbOptionISChecked = false

    private lateinit var radioGroup: RadioGroup
    private lateinit var kgRadio: RadioButton
    private lateinit var lbRadio: RadioButton

    private var gender = ""

    private lateinit var lbKgUnitTv: TextView

    private lateinit var weightInputEt: EditText

    private var weight = 48f
    private var w = 0f


    private val firstRunKey =
        sharedPreferences.getBoolean(AppUtils.FIRST_RUN_KEY_WATER_TRACKER, true)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_page_water_tracker)

        /* supportActionBar!!.title = ""
         supportActionBar!!.setDisplayHomeAsUpEnabled(true)*/

        dialog = Dialog(this)
        openDialog()

        coordinatorLayoutWaterTracker = findViewById(R.id.coordinatorLayoutWaterTracker)
        val closeBtn = dialog.findViewById<ImageView>(R.id.dialog_cancel_btn)
        lbKgUnitTv = dialog.findViewById(R.id.lb_kb_unit_tv)

        weightInputEt = dialog.findViewById(R.id.input_weight_edit_text)
        kgRadio = dialog.findViewById(R.id.kg)
        lbRadio = dialog.findViewById(R.id.lb)

        val saveBtn = dialog.findViewById<TextView>(R.id.save_btn)

        radioGroup = dialog.findViewById(R.id.radio_group_water_tracker)
        genderLinearLayout = findViewById(R.id.gender_relative_layout)
        weightLinearLayout = findViewById(R.id.weight_relative_layout)

        continueButton = findViewById(R.id.continue_btn)

        genderOption = findViewById(R.id.gender_option_water_tracker)
        userWeight = findViewById(R.id.weight_input_water_tracker)

        updateDetails()

        if (firstRunKey) {
            kgRadio.isChecked = true

            if (kgRadio.isChecked) {
                kgOptionISChecked = true

            } else if (lbRadio.isChecked) {
                lbOptionISChecked = true
            }

            lbRadio.setOnCheckedChangeListener { _, lbIsChecked ->
                saveRadioData("LB_CHECKED_WATER_TRACKER", lbIsChecked)
                lbOptionISChecked = lbIsChecked
            }

            kgRadio.setOnCheckedChangeListener { _, kgIsChecked ->
                saveRadioData("KG_CHECKED_WATER_TRACKER", kgIsChecked)
                kgOptionISChecked = kgIsChecked
            }



            radioGroup.setOnCheckedChangeListener { _, checkedId ->

                if (weightInputEt.text.isEmpty()) {
                    return@setOnCheckedChangeListener
                } else {

                    w = weightInputEt.text.toString().toFloat()
                    val dec = DecimalFormat("###.#")

                    if (checkedId == R.id.kg) {
                        w = (w / 2.2046).toFloat()
                        val fWeight = dec.format(w).toFloat()
                        weightInputEt.setText("$fWeight")
                        lbKgUnitTv.text = "kg"

                    } else if (checkedId == R.id.lb) {
                        w = (w * 2.2046).toFloat()
                        val fWeight = dec.format(w).toFloat()
                        weightInputEt.setText("$fWeight")
                        lbKgUnitTv.text = "lb"

                    }
                }

                weightInputEt.setSelectAllOnFocus(true)
                weightInputEt.selectAll()
            }
        }

        kgRadio.setOnClickListener {
            selectEditTextInput()
        }

        lbRadio.setOnClickListener {
            selectEditTextInput()
        }



        genderLinearLayout.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(
                this@UserDetailsPageWaterTracker, R.style.BottomSheetDialogTheme
            )

            val bottomSheetView = LayoutInflater.from(applicationContext).inflate(
                R.layout.layout_bottom_sheet_water_tracker,
                findViewById<LinearLayout>(R.id.bottomSheet)
            )

            bottomSheetView.findViewById<View>(R.id.male).setOnClickListener {
                genderOption.text = "Male"
                bottomSheetDialog.dismiss()
            }

            bottomSheetView.findViewById<View>(R.id.female).setOnClickListener {
                genderOption.text = "Female"
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }

        continueButton.setOnClickListener {
            saveInformation()
        }



        weightLinearLayout.setOnClickListener {
            dialog.show()
            weightInputEt.setText("$weight")
            weightInputEt.requestFocus()
            weightInputEt.setSelectAllOnFocus(true)
            weightInputEt.selectAll()
            dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
            dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)



            saveBtn.setOnClickListener {

                if (weightInputEt.text.toString().isEmpty()) {

                    Toast.makeText(this, "Please enter weight", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else if (!kgRadio.isChecked && !lbRadio.isChecked) {

                    Toast.makeText(this, "Please select the unit", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }


                if (kgOptionISChecked) {
                    if (weightInputEt.text.toString()
                            .toFloat() > 200 || weightInputEt.text.toString().toFloat() < 20
                    ) {
                        Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }


                } else if (lbOptionISChecked) {
                    if (weightInputEt.text.toString()
                            .toFloat() > 441 || weightInputEt.text.toString().toFloat() < 44
                    ) {
                        Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener

                    }

                }
                weight = weightInputEt.text.toString().toFloat()


                if (kgOptionISChecked) {
                    userWeight.text = "$weight kg"
                } else if (lbOptionISChecked) {
                    userWeight.text = "$weight lb"
                }

                dialog.dismiss()
            }

            closeBtn.setOnClickListener {
                weightInputEt.clearFocus()
                weightInputEt.requestFocus()
                dialog.dismiss()
            }
        }
    }

    private fun selectEditTextInput() {
        w = weightInputEt.text.toString().toFloat()
        val dec = DecimalFormat("###.#")
        val fWeight = dec.format(w).toFloat()
        weightInputEt.setText("$fWeight")
        weightInputEt.setSelectAllOnFocus(true)
        weightInputEt.selectAll()
    }

    private fun openDialog() {

        dialog.setContentView(R.layout.dialog_weight_input)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(true)
    }


    private fun saveInformation() {

        if (genderOption.text.isEmpty()) {

            val snack = Snackbar.make(
                coordinatorLayoutWaterTracker,
                "Please select gender",
                Snackbar.LENGTH_SHORT
            )
            snack.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
            snack.show()

            return
        }


        if (userWeight.text.isEmpty()) {

            val snack = Snackbar.make(
                coordinatorLayoutWaterTracker,
                "Please enter weight",
                Snackbar.LENGTH_SHORT
            )
            snack.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
            snack.show()
            return
        }


        gender = genderOption.text.toString()

        val finalWeight = userWeight.text.toString()

        saveRadioData("KG_CHECKED_WATER_TRACKER", kgOptionISChecked)
        saveRadioData("LB_CHECKED_WATER_TRACKER", lbOptionISChecked)

        val editor = sharedPreferences.edit()
        editor.putBoolean(AppUtils.FIRST_RUN_KEY_WATER_TRACKER, false)
//      editor.putInt(AppUtils.WEIGHT_KEY_WATER_TRACKER, weight.toInt())
        editor.putString(AppUtils.WEIGHT_KEY_WATER_TRACKER, finalWeight)
        editor.putString(AppUtils.GENDER_KEY_WATER_TRACKER, gender)
        // val totalIntake = AppUtils.calculateIntake(weight.toInt() ,180)
        editor.apply()

        Log.e("kgUnit", " =========  $kgOptionISChecked")
        Log.e("lbUnit", " =========  $lbOptionISChecked")
        Log.e("Gender", " =========  $gender")
        Log.e("Weight", " =========  $weight")
        //Log.e("TotalIntake", " =========  $totalIntake")

        val intent = Intent(this, HomeWaterTracker::class.java)
        startActivity(intent)
        finishAffinity()
    }

    private fun saveRadioData(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun updateRadio(key: String):
            Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    private fun updateDetails() {
        if (!firstRunKey) {
            loadData()
            updateView()
        }
    }

    private fun loadData() {
        kgOptionISChecked = updateRadio("KG_CHECKED_WATER_TRACKER")
        lbOptionISChecked = updateRadio("LB_CHECKED_WATER_TRACKER")
    }

    private fun updateView() {
        kgRadio.isChecked = kgOptionISChecked
        lbRadio.isChecked = lbOptionISChecked
    }



}
