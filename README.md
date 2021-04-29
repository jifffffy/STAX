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

How to use:
---------

create a test xml:
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE stax SYSTEM "stax.dtd">
<stax>
    <defaultcall function="Main"/>
    <function name="Main">
        <sequence>
            <dis>
                <sequence>
                    <open>'your.xfs.sp.logicalname'</open>
                    <status/>
                    <script>
                        deviceStatus = json.loads(STAFResult)
                    </script>
                    <if expr="'ONLINE' in deviceStatus['device']">
                        <return>[0, '']</return>
                    </if>
                    <reset>
                        {
                          'number': '0',
                          'retract': {
                            'retractArea': 'RA_RETRACT',
                            'outputPosition': 'NULL',
                            'index': '0'
                          },
                          'outputPosition': 'NULL',
                        }
                    </reset>
                    <dialog>STAFResult</dialog>
                </sequence>
            </dis>
        </sequence>
    </function>
</stax>
```
this xml script do:
- 1、use `dis` a CEN/XFS cdm service to `open` a connection.
- 2、use `status` to get device status
- 3、check the device status is ONLINE
- 4、if not then `reset`

current STAX service support xml elements:
```
<!ENTITY % task       'control-media | beep | card-unit-info | 
                       fig | release | export-rsa-issuer-signed-item | 
                       set-guid-light | raw-data | ccu | 
                       block | if | form-list | 
                       cash-unit-info | signalhandler | eject-slot-start | 
                       epp | retain-slot-end | cash-in-start | 
                       retain-card | script | call-with-map | 
                       delay | ukey-isr | doc | 
                       reset | terminate | reset-count | 
                       card-isr | media-list | start-exchange | 
                       status | read-form | log | 
                       eject-card | configure-note-types | cash-in-end | 
                       digitalio | bcr | initialization | 
                       set-cash-unit-info | iterate | print-form | 
                       break | read-input | rtn | 
                       testcase | breakpoint | call | 
                       write-raw-data | throw | retract | 
                       cash-in-rollback | end-exchange | return | 
                       open | acc | import-rsa-public-key | 
                       retain-slot-start | action-keys | read-raw-data | 
                       hold | dis | loop | 
                       continue | raise | dispense-card | 
                       mcr | enable-events | cash-in-status | 
                       mcr-idc | dispense | trd | 
                       jnl | import-key | sequence | 
                       set-card-unit-info | idr | currency-exp | 
                       try | job | fdk-keys | 
                       psb | retain-slot-start-ex | import | 
                       crypt | nop | stafcmd | 
                       timer | dialog | parallel | 
                       hcr | paralleliterate | note-type-list | 
                       read-image | on | rethrow | 
                       process | capabilities | ini | 
                       isr-idc | message | cash-in | 
                       call-with-list | rpt | slots-info | 
                       open-shutter | close-shutter | tcstatus'>
```

License
=====================
Code of STAX is licensed under [Apache License 2.0][10].