Install: http://download.eclipse.org/eclipse/downloads/drops4/R-4.3.2-201402211700/winPlatform.php
Install: http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html

help -> install new software -> enter url in the Work with box
http://download.eclipse.org/egit/updates
Check all the Egit boxes and install


help -> install new software -> enter url in the Work with box
http://download.eclipse.org/technology/m2e/milestones/1.5
Check all m2e boxes and install

File -> Import project -> Git -> Projects from Git -> Clone URI

Enter git@github.com:soccermafia89/Hanabi.git in url
Set the protocol to https
No need to enter other information

Keep clicking next when possible

At some point it should prompt you where to save your git repo remember this location.

File -> Import project -> maven -> existing maven project -> enter the file path to the git repo.  It should be the parent directory which contains pom.xml then keep clicking next

Once imported the maven errors shown are due to a bug in the m2e plugin, not the pom file itself.

Right click on project -> run as JUnit test -> allow java permission to access the web (will download dependencies from the internet)

The project should build successfully! In the console you should see 

********************************************
********         Test Deck         *********
********************************************

in the output (may need to scroll up).



---------
Generate SSH key:

Go to Window -> preferences -> Key Management -> generate RSA key and save.  Note the save location.  Open the .pub file.  And email me the public key contents.  
Never ever share your private key.


Pushing git changes to version control:

Right click project -> team -> push -> custom URI

ssh://github.com/soccermafia89/Hanabi.git (do not manually enter this, it will construct itself as you enter the information below)
github.com
soccermafia89/Hanabi.git

user: git
check: secure in store