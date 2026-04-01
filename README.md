This project was created in order to test the new library **Remote Compose** (https://developer.android.com/jetpack/androidx/releases/compose-remote).

### Why people are interested in Remote Compose

- Helps with KYC integrations, where some banks need to update the forms every so often.
- Helps with drawing payment cards, where the labels can be positioned depending on the background and the logo.
- Helps with showing user information related to info cards (health insurance, e-wallet, ...), where the server can decide what to show and how to show it.

### Just show me an example!

You can find all the examples under the package _examples_ in the **main** source folder.
All examples will rely on the following 2 concepts:
a 'Creator' (the page/ui that creates the **RemoteComposable** content, and renders it using a previewer for testing purposes),
and a 'Player' (the page/ui that will render the **RemoteComposable** content).
Start with the _simple_ one if you want to see how Remote Compose works.

_Some notes_:
- I had to code an alternate remote preview class into the project in order to be able to change the modifiers. The simple example adds a button that is external to the remote preview.
- I had to create a custom annotation (**DefaultPreview**) so that I can use both Compose and Remote Compose previews in the project (as of version 1.0.0-alpha07, Remote previews mess up the locales for the other previews).
- I could not find a RemoteButton component in version 1.0.0-alpha07 of Remote Compose.
- There is a bug with RemoteText (as of version 1.0.0-alpha07): the default color used is different between overloads; the version that takes a **String** uses Black, and the version that takes a **RemoteString** uses White (fallback).

More examples should be added in the future. Please let me know of any mistakes, optimizations, issues, ...

**N.B.**: The library looks promising for server-driven UIs, hopefully Google Play does not enforce a rule about changing UIs
after an app was approved (think Apple), and start rejecting apps that use it (because of the possiblity to modify the UI/UX significantly without approval).

_Checklist_:
- [ ] Add RemoteButton when it is available.
- [ ] Add more advanced example with image (and async image loading).
- [x] Add one or more examples with states (_RemoteFloat_, _RemoteString_, ...).
- [x] Add an example using RemoteCanvas.
- [ ] Add one or more examples with states inside RemoteCanvas.
