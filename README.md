# csd-420
csd-420 Advanced Java

## Run the JavaFX Card App in VS Code

The JavaFX assignment app is located at `module-1/Assignment/RandomCardDisplay.java` and expects the card images in `module-1/Assignment/cards`.

### 1. Install the VS Code Java tools

Install these extensions if they are not already enabled:

- Extension Pack for Java
- Language Support for Java by Red Hat
- Debugger for Java

### 2. Download a local JavaFX SDK

1. Download the JavaFX SDK for your JDK version from Gluon.
2. Extract it to a local folder, for example: `C:\javafx-sdk-24.0.1`
3. Create a user environment variable named `JAVAFX_HOME` that points either to the SDK folder or directly to its `lib` folder.

On Windows PowerShell, you can set it for the current session with:

```powershell
$env:JAVAFX_HOME = 'C:\javafx-sdk-24.0.1'
```

### 3. Launch the app in VS Code

This repository includes `.vscode/launch.json` configured to run the app with JavaFX.

1. Open the workspace root in VS Code.
2. Make sure `JAVAFX_HOME` points to your extracted JavaFX SDK folder or its `lib` directory.
3. Open `Run and Debug`.
4. Select `Run RandomCardDisplay (JavaFX)`.
5. Start debugging.

### 4. If JavaFX imports still show as unresolved

The app can still run once the debugger has the correct JavaFX module path, but if the editor continues to flag `javafx.*` imports, confirm that:

- VS Code is using a full JDK, not a JRE.
- The JavaFX SDK version matches your installed JDK closely enough to run.
- `JAVAFX_HOME` points to either the SDK folder or the `lib` folder inside it.
- The launch configuration is using the correct path.

### 5. Build the app in VS Code

This repository now includes a default build task:

1. Open `Terminal > Run Build Task`.
2. Select `Build RandomCardDisplay (JavaFX)` if VS Code does not pick it automatically.
