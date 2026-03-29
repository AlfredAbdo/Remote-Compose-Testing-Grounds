This project was created in order to test the new library **Remote Compose** (https://developer.android.com/jetpack/androidx/releases/compose-remote).

### Why people are interested in Remote Compose

- Helps with KYC integrations, where some banks need to update the forms every so often.
- Helps with drawing payment cards, where the labels can be positioned depending on the background and the logo.
- Helps with showing user information related to info cards (health insurance, e-wallet, ...), where the server can decide what to show and how to show it.

### Just show me an example!

For now, a simple working example is provided (in the package _remote_, the classes are **RemoteCreatorPage** and **RemotePlayerPage**). It showcases the following: **RemoteColumn**, **RemoteText** and **RemoteSpacer** (with weight 1).

_Some notes_:
- I had to code an alternate remote preview class into the project in order to be able to change the modifiers. The simple example adds a button that is external to the remote preview.
- I had to create a custom annotation (**DefaultPreview**) so that I can use both Compose and Remote Compose previews in the project (as of version 1.0.0-alpha07, Remote previews mess up the locales for the other previews).
- I could not find a RemoteButton component in version 1.0.0-alpha07 of Remote Compose.

More examples should be added in the future. Please let me know of any mistakes, optimizations, issues, ...

The library looks promising for server-driven UIs (especially for KYC integrations, where some banks need to update the forms every so often), hopefully Google Play does not enforce a rule about changing UIs
after an app was approved (think Apple), and start rejecting apps that use it (because of the possiblity to modify the UI/UX significantly without approval).

_Checklist_:
- [ ] Add RemoteButton when it is available.
- [ ] Add more advanced example with image.
- [ ] Add an example using RemoteCanvas.
- [ ] Add one or more examples with states (_RemoteFloat_, _RemoteString_, ...).
