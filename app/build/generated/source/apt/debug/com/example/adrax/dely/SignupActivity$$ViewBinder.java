// Generated code from Butter Knife. Do not modify!
package com.example.adrax.dely;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SignupActivity$$ViewBinder<T extends com.example.adrax.dely.SignupActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493007, "field '_nameText'");
    target._nameText = finder.castView(view, 2131493007, "field '_nameText'");
    view = finder.findRequiredView(source, 2131493001, "field '_emailText'");
    target._emailText = finder.castView(view, 2131493001, "field '_emailText'");
    view = finder.findRequiredView(source, 2131493002, "field '_passwordText'");
    target._passwordText = finder.castView(view, 2131493002, "field '_passwordText'");
    view = finder.findRequiredView(source, 2131492869, "field '_firstnameText'");
    target._firstnameText = finder.castView(view, 2131492869, "field '_firstnameText'");
    view = finder.findRequiredView(source, 2131493008, "field '_surnameText'");
    target._surnameText = finder.castView(view, 2131493008, "field '_surnameText'");
    view = finder.findRequiredView(source, 2131493009, "field '_midnameText'");
    target._midnameText = finder.castView(view, 2131493009, "field '_midnameText'");
    view = finder.findRequiredView(source, 2131493010, "field '_selnumText'");
    target._selnumText = finder.castView(view, 2131493010, "field '_selnumText'");
    view = finder.findRequiredView(source, 2131493011, "field '_aboutText'");
    target._aboutText = finder.castView(view, 2131493011, "field '_aboutText'");
    view = finder.findRequiredView(source, 2131493012, "field '_signupButton'");
    target._signupButton = finder.castView(view, 2131493012, "field '_signupButton'");
    view = finder.findRequiredView(source, 2131493013, "field '_loginLink'");
    target._loginLink = finder.castView(view, 2131493013, "field '_loginLink'");
  }

  @Override public void unbind(T target) {
    target._nameText = null;
    target._emailText = null;
    target._passwordText = null;
    target._firstnameText = null;
    target._surnameText = null;
    target._midnameText = null;
    target._selnumText = null;
    target._aboutText = null;
    target._signupButton = null;
    target._loginLink = null;
  }
}
