![Project Tests](https://github.com/KIU-GEDGADA/EosEngine/actions/workflows/Tests.yml/badge.svg)

![EosEngine Light](/branding/EosEngineLogo-Light.png#gh-dark-mode-only)
![EosEngine Dark](/branding/EosEngineLogo-Dark.png#gh-light-mode-only)

## Appendix

* [Introduction](#eosengine---java-game-engine)
* [Installation](#installation)
* [Demo](#demo)
    * [How to run Demo](#how-to-run-demo)
    * [Demo Preview](#demo-preview)
        * [First ever Demo](#first-ever-demo)
        * [Most recent Demo](#most-recent-demo)
* [Overall Idea](#overall-idea)
* [Get Started](#get-started)
    * [Creating your game class](#creating-your-game-class)
    * [Essentials to add for the game to work](#essentials-to-add-for-the-game-to-work)
    * [Adding Different Components](#adding-different-components)
        * [Camera](#camera)
        * [Model](#model)
        * [Item](#item)
        * [Lighting](#lighting)
* [License Information](#license)
* [Authors](#authors)

------------

# EosEngine - Java Game Engine

EosEngine is Java Game Engine based on [LWJGL](https://www.lwjgl.org). EosEngine was created
by "Kutaisi International University" students as a part of the university course "Basic Game Engine Development".

## Installation

Before continuing, you will need the following:

* _git_ installed on the computer
* _Java IDE_ (Example: _IntelliJ, Eclipse_)
* (Dependencies that are needed, will be installed automaticlly when you will build the project)

Install EosEngine with git:

* Click the "CODE" on the right top corner of the project and click _HTTPS_ then go ahead and copy the link which would
  look like this: ```https://github.com/KIU-GEDGADA/EosEngine.git```
* Next open terminal in the file you want to clone the project to and type this:

```bash
  git clone [INSERT LINK HERE]
```

* And You are DONE! You have successfully added project to you computer.

## Demo

### How to run Demo

* Clone the repository
* Open the project
* Locate ```src/main/java/finalDemo/Preview.java```
* run the project

### Demo Preview

#### First ever Demo

![Demo](https://github.com/KIU-GEDGADA/EosEngine/blob/b2d9efcfe668eb016a3f57638639e2d0b99435e1/branding/DemoPreview.gif)

#### Most recent Demo

![Demo](https://github.com/KIU-GEDGADA/EosEngine/blob/b2d9efcfe668eb016a3f57638639e2d0b99435e1/branding/Demo2.gif)

## Overall Idea

Our main goal was to make non-euclidean easily accessible game engine in `Java`. With our game engine you can easily 
interact with projections and views of an item, which makes it ideal for non-euclidean worlds.

## Get Started

Every project's step one is to get to know the documentation. So before continuing we _highly recommend_
you to read documentation, which is located below.

### Creating your game class

Of course the first step when creating a game is to create ```Game.java```. Make sure that
the class implements Entity like this:

```
public class Game implements Entity{
    REST OF THE CODE HERE
}
``` 

this way all the methods you will need be added automatically. That's it! You have successfully
created your own game class!

### Essentials to add for the game to work

There are some things we need to add to make sure that the game engine can run the game smoothly without
issues. **Items**, When adding new item to the game like in the demo:

```
Item item = new Item("Cube", new Model(new Mesh("res/models/goodCube.obj")), shaders, texture1);
Renderer.addItem(item);
```

We need to make sure to add it to the `Renderer` so the item can be rendered. Light initialization can be
seen [!here](#lighting)

Second thing we need to make sure is that after adding the item we need to destroy it in the overridden
method called `Destroy()` Like this:

```
@Override
public void destroy() {
    item.destroy();
}
```

### Adding Different Components

#### Camera

To add `Camera` to the game and actually start manipulating it you should do the following:

```
camera = Camera.getInstance();
```

Because we used design pattern called _Singleton_, we are able to do this.
If you want (in the future) to change you can do this:

```
camera.setFOV(30).setPosition(3,4,5); //So on.. so on...
```

If you want to move position of the `Camera` during the game you should use this method:

```
camera.movePosition(xVelocity, yVelocity, zVelocity);
```

as this method insures that camera directions does not get messed up.

#### Model

This is a class that holds `Texture` and `Mesh` objects. It makes sure to attach those objects together.
Creation of `Model` is pretty straight forward:

```
Model model = new Model(new Mesh(PATH or PARAMETERS), texture);
```

`Texture` is again optional.

#### Item

As we talked about in the last chapter `Item` needs few more functions to be called, so it can be rendered
correctly.
`Item` needs following parameters in the constructor:

* Name
* Model (check the last chapter)
* Shaders (check the last chapter)
* Texture (Optional)

#### Generators
`Generators` is a `static` class, which generates models for certain scenarios. For example:
* Terrains
* Simple Objects
    * Triangles
    * Cubes

For now Generators only consist of _Terrain Generation_. So let's recap:
Here is how you create item:
```
Model model = new Model(Generators.generateTerrain()).setTexture(texture2, 0.1f);
terrain1 = new Item(POSITION, NAME, model, GIVENSHADERS);
```
So as you can see, we just changed constructor of model from `new Mesh(PATH)` to `Generators.generateTerrain()`.
With this you have successfully generated a terrain!

#### Material
`Material` class make sure to attach correct textures to correct meshes and adds few parameters including:
* ambientColor 
* diffuseColor
* specularColor

Now these parameters can be changed real-time or before execution. User does not need to create _material_ or pass it to
somewhere, as engine automatically creates it and stores it in model, so to retrieve material you just call:
`modelName.getMaterial()`. Material is also the one which is responsible for attaching texture to mesh therefore, if you
want to set texture, do it like this: `modelName.getMaterial().setTexture(PASS_TEXTURE)`
#### Lighting

For now our game engine supports two types of lights: `DirectionalLight` and `PointLight`. To use these correctly choose
the correct
shader when creating an item and create list with it:

```
Shader vs = new Shader("res/shaders/tv2vs.glsl");
Shader fs = new Shader("res/shaders/tv2fs.glsl");
List<Shader> shaders = List.of(new Shader[]{vs, fs});
```

then add it to item as list:

```
Model model = new Model(new Mesh("res/models/goodCube.obj")).setTexture(new Texture("res/textures/goodTexture.png"))
complexObject = new Item(pos6, "Complex Figure with Ligthing and texture", model, shaders);
```

Then you can create lighting of your type

```
PointLight pointLight = new PointLight(lightColor, new Vector3f(0,-5,0), 100f, 0, 0, 1);
```

And Finally add it to the Renderer

```
Renderer.addLight(pointLight);
```

_Make sure to add lights before items as doing it reverse may cause problems!_

## License

[MIT License](https://choosealicense.com/licenses/mit/)

## Authors

- [@giokepa](https://github.com/giokepa)
- [@doma](https://github.com/DoMa157)
- [@memory-hunter](https://github.com/memory-hunter)
- [@theInfernofire](https://github.com/theInfernofire)
