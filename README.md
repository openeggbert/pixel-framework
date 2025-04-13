# Pixel Game Framework

> ''This article is about the game framework. For other uses, see the [[Pixel (disambiguation)]].''

Pixel is the advanced [[game library]] ([[API]]) offering a streamlined interface for game development, which [[Open Eggbert]] is built on.

It is designed with flexibility and future expandability in mind.

## API
Pixel is set of [[Java]] interfaces and several classes.

To use the Pixel Game Library, you have to use a backend (or create a new one), which implements these interfaces.

## Key Features

### Defined Interface
Offers a consistent and clear interface for game development, simplifying transitions between various implementations.

### LibGDX Integration
Originally based on LibGDX, the library allows seamless integration while offering a simplified approach.

### Flexible and Future-Ready
Built to be flexible, Pixel Game Library supports future integration with other game libraries and frameworks beyond LibGDX.

## Backends
*Note: This table was taken from: https://issues.openeggbert.com*

Now it is worked on the [[LibGDX]] backends.

Other backends are planned to be implemented in the future.

| Backend Name | Description |
|--------------|-------------|
| Pixel Backend Android | Backend for the Android operating system (alternative to LibGDX) |
| Pixel Backend Android Canvas | Drawing graphics on Android Canvas |
| Pixel Backend Android OpenGL | Pixel Backend Android OpenGL |
| Pixel Backend Android Vulkan | Drawing graphics via Vulkan on Android operating system |
| Pixel Backend Desktop | Backend for the Desktop (Windows, Linux, MacOS) (alternative to LibGDX) |
| Pixel Backend Desktop Canvas Java2D | Drawing graphics on Java2D canvas |
| Pixel Backend Desktop Canvas JavaFX | Drawing graphics on JavaFX Canvas |
| Pixel Backend Desktop Lwjgl OpenGL | Pixel Backend Desktop Lwjgl OpenGL |
| Pixel Backend Desktop Lwjgl Vulkan | Drawing graphics via Lwjgl Vulkan |
| Pixel Backend LibGDX | Pixel Backend LibGDX |
| Pixel Backend LibGDX Android | Pixel Backend LibGDX Android |
| Pixel Backend LibGDX GWT | Pixel Backend LibGDX GWT |
| Pixel Backend LibGDX Lwjgl3 | Pixel Backend LibGDX Lwjgl3 |
| Pixel Backend Web | Backend for the web (alternative to LibGDX) |
| Pixel Backend Web Canvas | Drawing graphics on Canvas in the web browser |
| Pixel Backend Web GWT | Pixel Backend Web GWT |
| Pixel Backend Web TeaVM | Pixel Backend Web TeaVM |
| Pixel Backend Web WebGL | Pixel Backend Web WebGL |
| Pixel Backend Web WebGPU | Drawing graphics via WebGPU in the web browser |

## Scene 2D
Scene 2D features are located in the package `com.pixelgamelibrary.api.scene2d`.

Pixel Scene 2D is inspired by LibGDX Scene 2D.

## How to build
```bash
./gradlew clean build publishToMavenLocal
```

## Todos

### Rework the LZMA compression support in the Pixel Game Library

1. **Android**

You can use the LZMA SDK on Android without any issues, as Android is based on Java SDK, and the LZMA SDK supports pure Java. The process is straightforward, similar to using any other Java library.

**How to use:**

- Add the LZMA SDK library to your project.
- Use the standard LZMA classes for compression and decompression.

Since LZMA SDK is purely Java-based, it does not require native libraries or JNI, which is convenient for Android deployment.

2. **GWT**

Using the LZMA SDK in GWT is more challenging due to differences between the JVM and the JavaScript environment that GWT uses. GWT compiles Java into JavaScript, meaning some features, such as native libraries or JVM-dependent classes, may not work.

**Issue:** The LZMA SDK for Java may not be compatible with GWT, as it uses classes and methods not supported in GWT's JavaScript runtime.

**Possible Solution:** For GWT, you can use a JavaScript implementation of LZMA, like `lzma.js`. You can include this library in your GWT project and call it using JavaScript Native Interface (JSNI).

**Example: Calling LZMA in GWT (via JSNI):**

```java
public class LzmaWrapper {

   public native void compress(String input, LzmaCallback callback) /*-{
      var lzma = new $wnd.LZMA();
      lzma.compress(input, 1, function(result) {
         callback.@com.yourpackage.LzmaWrapper.LzmaCallback::onCompressed(*)(result);
      });
   }-*/;

   public native void decompress(String input, LzmaCallback callback) /*-{
      var lzma = new $wnd.LZMA();
      lzma.decompress(input, function(result) {
         callback.@com.yourpackage.LzmaWrapper.LzmaCallback::onDecompressed(*)(result);
      });
   }-*/;

   public interface LzmaCallback {
      void onCompressed(String result);
      void onDecompressed(String result);
   }
}
```

**Recommendations:**

- For **Android**: Use the LZMA SDK directly.
- For **GWT**: Use a JavaScript version of LZMA (such as `lzma.js`) and integrate it via JSNI.

This way, you can ensure compatibility with both platforms.

[[Category:Technologies used by Open Eggbert]]  
[[Category:Obsolete]]
