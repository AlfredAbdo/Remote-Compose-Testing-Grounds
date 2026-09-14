# Adaptive Example

An adaptive example that shows the intended method to handle different screen sizes (adaptive concept).
It showcases the following in the creator: **RemoteFitBox**.

![Adaptive Example](images/example.gif)<br>Adaptive Example

_Some notes_:

- **RemoteFitBox** works as such: it chooses the 1st child that fits; the intention is to provide all the different
  composables from the largest to the smallest, unlike other adaptive methods where one can check the class of the
  device (phone, tablet/fold, etc.). The composables need to allocate a minimum size and not fill the parent, in order
  to let **RemoteFitBox** pick the one that fits; the fallback, i.e. the smallest, does not need to.