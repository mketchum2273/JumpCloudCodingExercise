# JumpCloudCodingExercise

Setup:
  - This coding exercise was completed in Java
  - The JDK used was Amazon Corretto 11. The download/installation instructions are as follows:
    1. Go to https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html
    2. Choose the msi file (This is Windows-specific...if you are on some other operating system then you'll have to choose that particular download)
    (Note: While going through the setup wizard, make a note of where Amazon Corretto is being installed on your machine)
    3. When the wizard opens, click Next --> Next --> Install
    4. Click "Yes" in the dialog the pops up
    5. Click "Finish"
  - The IDE used was IntelliJ IDEA Community Edition
    1. Go to https://www.jetbrains.com/idea/download/#section=windows
    2. Download the (free) Community Edition
    3. Select "Run" if prompted to do so
    4. Click "Yes" on the popup
    5. Click Next --> Next --> Select 32- or 64-bit as appropriate for your system, as well as selecting ".java", ".groovy", and ".kt" under Create Associations --> Next --> Install
    6. Open IntelliJ IDEA --> leave "Do not import settings" checked and click "OK" --> Accept the privacy policy --> Click "Don't Send" --> Choose your background preference and click Next --> Next --> Click "Start using IntelliJ IDEA"
    7. Click the "Configure" dropdown in the bottom right and select "Structure for New Projects"
    8. Click "Project" under "Project Settings"
    9. Under "Project SDK", click New --> JDK
    10. Navigate to the location where Amazon Corretto was installed and select the JDK (should be jdk11.0.4_10 or something similar) and click "OK"
    11. Now that your JDK is set under "Project SDK", click "OK"
    (Note: Some of the configuration steps below may not be necessary for the successful execution of the code I've provided for this exercise, but they are the steps I went through when originally setting up my IDE and so I thought it best not to omit any of them.)
    12. Click the "Configure" dropdown in the bottom right and select "Settings"
    13. On the left side, click "Editor", "General", and then click Auto Import
    14. Select "Add unambiguous imports on the fly" and "Optimize imports on the fly (for current project)
    15. On the left side, click "Appearance"
    16. Ensure that "Show line numbers" is checked
    17. On the left side, click "Code Folding"
    18. De-select "Imports", "One-line method", "Closures (anonymous classes implementing one method, before Java 8)", and "Generic constructor and method parameters"
    19. Click "OK"
    
