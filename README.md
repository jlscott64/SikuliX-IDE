Sikuli IDE 1.1.0 developement branch
===
**planned release date December 15th** - betas will be available before
<hr />
<br />
**Sikuli IDE 1.0.1<br />Service Update per August 12th, 2013**
<hr />
**MANDATORY ;-) --->>>** [Read the Release Notes carefully to avoid problems from the beginning](https://github.com/RaiMan/SikuliX-IDE/wiki/Release-Notes-IDE)

[to download setup-sikuli.jar version 1.0.1 start here](http://www.sikuli.org/download.html)

Sikuli IDE is targeted at people who want to develop and run scripts using Sikuli features with one of the supported scripting languages (currently Python - more scripting languages to come) in a lightweight IDE with special support for the visual approach provided by Sikuli.
<br /><br />
The downloadable packages of Sikuli IDE contain everything needed for all purposes (but it should not be used with standalone Jython to avoid conflicts with the bundled Jython - use sikuli-java.jar instead from [Sikuli API](https://github.com/RaiMan/SikuliX-API))
<br /><br />
For the use of Sikuli features in Java programs, other Java based languages or Java aware scripting languages currently not supported by Sikuli IDE you should use [Sikuli API](https://github.com/RaiMan/SikuliX-API) with other IDE's like Eclipse, NetBeans, ...

Same goes for people who want to develop, run and debug scripts using SikuliX-IDE supported scripting languages in other IDE's like Eclipse, Netbeans, ...
<br /><br />
This repo is **fully Maven**, so a fork of this repo can be directly used as project in NetBeans/Eclipse/... or with mvn on commandline. <br />
It depends on [Sikuli API](https://github.com/RaiMan/SikuliX-API), [Sikuli Jython](https://github.com/RaiMan/SikuliX-Jython) and [Sikuli Basics](https://github.com/RaiMan/SikuliX-Basics).<br />
[A more detailed info on usage, contents and production of standalone runnable packages](https://github.com/RaiMan/SikuliX-API/wiki/Maven-support)
<br /><br />
**Roadmap**
 - **2013 September:** start developement SikuliX 1.1
<br /><br />
 - **2013 December 15:** release of SikuliX 1.1
  - new features tbd.
<br /><br />
 - **2014:** new versions in May and December

**History**
 - this is based on the developement at MIT (Tsung-Hsiang Chang (Sean aka vgod) and Tom Yeh) which was discontinued end 2011 (https://github.com/sikuli/sikuli) with a latest version called Sikuli X-1.0r930.
 - and the [follow up repo](https://github.com/RaiMan/Sikuli12.11), where I prepared the creation of a final version 1.0
 - in April 2013 I decided, to divide Sikuli into the 2 packages Sikuli IDE and [Sikuli API](https://github.com/RaiMan/SikuliX-API), to better support future contributions.

**Support**
 - until otherwise noted: [questions, requests and bugs can still be posted here](https://answers.launchpad.net/sikuli)
 - the wiki in this repo will be used extensively to document anything (taking over this roll from the webpage and lauchpad)
 - you might always post an issue with any content in this repo of course

**Contribution**
 - pull requests are always welcome
 - everyone is welcome to add interesting stuff, experiences, solutions to the wiki in this repo
