#!/bin/bash

# update dependencies
git submodule init
git submodule update


# generate api
base=$(dirname "$0")
out=$base/idl/generated
tmp=$base/idl/generated-tmp
script=$base/tools/djinni/src/run
api=$base/idl/api.djinni

out_cpp=$tmp/out-cpp
out_objc=$tmp/out-objc
out_jni=$tmp/out-jni
out_java=$tmp/out-java/com/movies/api

java_package=com.movies.api
objc_prefix=MV
cpp_namespace=movies

rm -rf $tmp
$script --idl $api \
    --cpp-out $out_cpp \
    --cpp-namespace $cpp_namespace \
    --ident-cpp-enum FooBar \
    --ident-cpp-field fooBar \
    --ident-cpp-method fooBar \
    --ident-cpp-type FooBar \
    --ident-cpp-enum-type FooBar \
    --ident-cpp-type-param fooBar \
    --ident-cpp-local fooBar \
    --ident-cpp-file FooBar \
    \
    --objc-out $out_objc \
    --objcpp-out $out_objc \
    --objc-type-prefix $objc_prefix \
    --ident-objc-enum FooBar \
    --ident-objc-field fooBar \
    --ident-objc-method fooBar \
    --ident-objc-type FooBar \
    --ident-objc-type-param fooBar \
    --ident-objc-local fooBar \
    --ident-objc-file FooBar \
    \
    --java-out $out_java \
    --java-package $java_package \
    --java-class-access-modifier "public" \
    --java-nullable-annotation "androidx.annotation.Nullable" \
    --java-nonnull-annotation "androidx.annotation.NonNull" \
    --ident-java-enum FooBar \
    --ident-java-field mFooBar \
    \
    --jni-out $out_jni \
    --ident-jni-class NativeFooBar \
    --ident-jni-file NativeFooBar \

mkdir -p $out
rsync -a --delete --checksum --itemize-changes $tmp/ $out
rm -rf $tmp
