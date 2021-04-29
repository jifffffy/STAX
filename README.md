STAX
================
Open Source STAF service .

Architect:
---------
![STAX](doc/STAX.png?raw=true "Title")

Features:
---------
- 1、 stax service upgrade jython2.5 to jython2.7;
- 2、 stax support function STAX event;
- 3、 stax support plugins with pf4j

Plugins:
--------
- 1、 xfs plugin using [xfs-service](https://github.com/sunyuyangg555/staf-services/tree/main/services/xfs)
- 2、 pe plugin support 
```
<ini>
    <query>
        <section name="xxx">
            <option name="YYY"></option>
        </section>
    </query>
</ini>

<beep hz="" vol=""></beep>

<dialog></dialog>

```

Build:
---------
Run the bellow command:
```
git clone https://github.com/sunyuyangg555/STAX.git
cd stax
gradle clean build
```

Use plugins

copy `build/plugins/*-*.zip` to `c:/STAF/service/stax/plugins` dir

License
=====================
Code of STAX is licensed under [Apache License 2.0][10].