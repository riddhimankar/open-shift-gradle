# S2I with gradle


#### basic-s2i

`$ oc project gradle-s2i-builder`
`$ oc new-build --name basic-gradle-s2i --context-dir basic-s2i --strategy docker -i openjdk18 https://github.com/riddhimankar/open-shift-gradle`
`$ oc new-app --name basic-gradle-s2i-app --context-dir app basic-gradle-s2i~https://github.com/riddhimankar/open-shift-gradle`

#### fabric8

`$ oc new-build --name fabric8-gradle-s2i --context-dir fabric8-s2i --strategy docker https://github.com/riddhimankar/open-shift-gradle`
`$ oc new-app --name fabric8-gradle-s2i-app --context-dir app fabric8-gradle-s2i~https://github.com/riddhimankar/open-shift-gradle`
