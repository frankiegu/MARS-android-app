package com.uta.mars.app

import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.animation.DecelerateInterpolator
import android.view.{View, ViewAnimationUtils}
import android.widget.ImageView
import com.dd.morphingbutton.impl.LinearProgressButton
import com.github.clans.fab.FloatingActionButton
import com.github.nscala_time.time.Imports._
import com.rengwuxian.materialedittext.MaterialEditText
import com.rengwuxian.materialedittext.validation.RegexpValidator
import com.uta.mars.R
import com.uta.mars.app.common._
import org.scaloid.common._

class RegistrationSetupAct extends BaseActivity {

  private lazy val regFAB    = find[FloatingActionButton](R.id.fab_reg)
  private lazy val regForm   = find[CardView](R.id.reg_form)
  private lazy val loginForm = find[CardView](R.id.login_form)
  private lazy val nextBtn   = find[LinearProgressButton](R.id.reg_next_btn)
  private lazy val cancelBtn = find[ImageView](R.id.cancel)
  private lazy val loginBtn  = find[LinearProgressButton](R.id.login_btn)

  private lazy val firstNameEt  = find[MaterialEditText](R.id.et_first_name)
  private lazy val lastNameEt   = find[MaterialEditText](R.id.et_last_name)
  private lazy val allEditTexts = Seq(firstNameEt, lastNameEt)

  override def onCreate(b: Bundle): Unit = {
    super.onCreate(b)
    setContentView(R.layout.screen_reg_setup)

    allEditTexts.map(_.addValidator(new RegexpValidator("Can't be blank", ".+")))

    loginBtn.morphToNormalBtn(R.string.login.r2str)
    nextBtn.morphToNormalBtn(R.string.next.r2str, R.color.md_white)
    nextBtn.onClick {
      if (allEditTexts.forall(_.validate())) { startActivity(RegistrationAct(firstNameEt.txt2Str, lastNameEt.txt2Str)) }
    }
    cancelBtn.onClick((v: View) => setupReturnTransition())
    setupEnterTransition()
  }

  override def onBackPressed(): Unit = {
    setupReturnTransition()
  }

  // Set up animations when changing from LoginAct to this activity.
  private def setupEnterTransition(): Unit = {
    val trans = R.transition.login_to_reg.r2Trans
    trans.onTransitionStart { (_, _) =>
      loginForm.startAnimation(R.anim.login_form_to_bg.r2anim)
      delay(1150.millis) {
        regFAB.setVisible()
        regForm.setVisible()
        ViewAnimationUtils.createCircularReveal(regForm, regForm.centerX, regForm.centerY, 56, regForm.getHeight)
          .setDuration(500)
          .onAnimationStart(_ => regFAB.setInvisible())
          .start()
      }
    }
    getWindow.setSharedElementEnterTransition(trans)
  }

  // Set up animations when returning to LoginAct from this activity.
  private def setupReturnTransition(): Unit = {
    ViewAnimationUtils.createCircularReveal(regForm, regForm.centerX, regForm.centerY, regForm.getHeight, 56)
      .setDuration(500)
      .interpolator(new DecelerateInterpolator())
      .onAnimationStart(_ => loginForm.startAnimation(R.anim.login_form_to_fg.r2anim))
      .onAnimationEnd(_ => regForm.setInvisible())
      .onAnimationEnd(_ => regFAB.setVisible())
      .start()

    delay(650.millis) {
      val returnTrans = R.transition.reg_to_login.r2Trans
      getWindow.setSharedElementReturnTransition(returnTrans)
      finishAfterTransition()
    }
  }
}
