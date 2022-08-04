# Comptool-Plugin
### Used to create compensation-based plugins for the comp-tool system.

## To Build a Plugin:

- Clone this repository into a folder:
```shell
git clone https://github.com/Guidewire-Software-Open-Source/comptool-plugin.git
```
- Change fields in `manifest.json` to match your new plugin name and version number.
```json
{
  "name": "my_plugin_name",
  "author": "my_name",
  "version": "0.0.0.1",
  "mainClass": "com.example.Plugin"
}
```

Note that the mainClass is the main running class of your new plugin. Also note that the version must be in the format `x.x.x.x`. These numbers are referred to as the release, the major, the minor, and the patch, respectively, and all must be present.

- Change the name of the generated jar in the `Makefile` to whatever name is wanted. Standardization suggests using the name `plugin_name-author_name-version`. Note that the `.jar` is left off of the end here.
```Makefile
JAR_NAME = plugin_name-author_name-0.0.0.1
```

- Create whatever code is required. Example code can be found in `com.example.Plugin`. It is best to delete this file once the code is understood and create your own file.
Note that the plugin name and path is important. The main plugin must use the path given in `manifest.json`. For example, if the main Plugin file is located in `com.foo.bar`, the `mainClass` must be `com.foo.bar.PluginClassName`. Note that this class name does not need be `Plugin`, but the class must extend `CompensationPlugin`.
- Once the plugin is made, it can then be compiled into a distributable `jar` file using the supplied `Makefile`. Please use the command corresponding to the given operating system on which the file is being compiled.
```shell
make macos
```
```shell
make windows
```
```shell
make linux
```


## Specifics of Writing a Plugin

A Plugin is used to represent possible monetary (or even non-monetary) data that is not already included within the current comptool architecture - for example, perhaps a new system of storing stock is different from that supplied to the user currently, or healthcare benefits which are not supported as of writing this. These can be created, converted to plugins, and added to comptool without recompiling or editing the source code of comptool itself.

A Plugin **must** extend `CompensationPlugin`, an abstract class found in `com.compensation.portal.plugins.CompensationPlugin.kt`. (Note that no files with `com.compensation.portal` are meant to be edited by the user; doing so will render the plugin unusable).

A Plugin can have UI-editable fields, defined within the `fieldTypes` dictionary. The `fieldType` corresponding to some field `x` defines the UI design for field `x`; it is upon the builder of the plugin to make sure that the correct field type is assigned. These fields are assigned in the constructor. For example, if `x` is of type `Int`, then the constructor contains:
```kotlin
constructor () {
    fieldTypes["x"] = PluginFieldTypes.INTEGER;
}
```

Note that the field type is assigned with a String, allowing for pseudo-reflection.
Also note that the constructor for a plugin **must** be an empty constructor, as this is what will be called by comptool when instantiating the plugin.

Fields are set and retrieved using the `setField` and `getField` method in `CompensationPlugin`. Use these methods to parse input and assign proper values. Note that as these methods are used, a field does not technically need to have the same value type as that in `fieldTypes` corresponding to it; instead, it must simply parse that `fieldType` and be able to respond with a value of the same type. Fields can also have different display names than their variable names. An example of such is `backendVariable` in `Plugin.kt`, which has the frontend name defined in the constructor of `"Frontend Name"`. This variable is also a `Float`, but shows up on the frontend as a `Array<Float>`. This shows that the frontend type and backend type need not match, but must be converted in the APIs for `setField` and `getField`.

`calculateCompensation (int year)` is called whenever the compensation for a given year must be calculated. It is assumed that this compensation is independent of any other possible plugins, fields, or years that may also be calculated. This function returns the calculation for a specific year, with year 1 being the first year that a given person is working at that job (as opposed to year 0). 


