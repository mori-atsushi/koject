package com.moriatsushi.koject.processor.dummy

import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeArgument
import com.google.devtools.ksp.symbol.KSTypeParameter
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSVisitor
import com.google.devtools.ksp.symbol.Location
import com.google.devtools.ksp.symbol.Modifier
import com.google.devtools.ksp.symbol.NonExistLocation
import com.google.devtools.ksp.symbol.Origin

class DummyKSClassDeclaration(
    override val annotations: Sequence<KSAnnotation>,
    override val classKind: ClassKind,
    override val isCompanionObject: Boolean,
    override val primaryConstructor: KSFunctionDeclaration?,
    override val superTypes: Sequence<KSTypeReference>,
    override val containingFile: KSFile?,
    override val docString: String?,
    override val packageName: KSName,
    override val parentDeclaration: KSDeclaration?,
    override val qualifiedName: KSName?,
    override val simpleName: KSName,
    override val typeParameters: List<KSTypeParameter>,
    override val declarations: Sequence<KSDeclaration>,
    override val isActual: Boolean,
    override val isExpect: Boolean,
    override val modifiers: Set<Modifier>,
    override val location: Location,
    override val origin: Origin,
    override val parent: KSNode?,
) : KSClassDeclaration {
    class Builder(
        private val simpleName: KSName,
        private val packageName: KSName,
    ) {
        val annotations = mutableListOf<KSAnnotation>()
        var classKind: ClassKind = ClassKind.CLASS
        var isCompanionObject: Boolean = false
        var primaryConstructor: KSFunctionDeclaration? = null
        val superTypes = mutableListOf<KSTypeReference>()
        var containingFile: KSFile? = null
        var docString: String? = null
        var parentDeclaration: KSDeclaration? = null
        var qualifiedName: KSName? = DummyKSName()
        val typeParameters = mutableListOf<KSTypeParameter>()
        val declarations = mutableListOf<KSDeclaration>()
        var isActual: Boolean = false
        var isExpect: Boolean = false
        val modifiers = mutableSetOf<Modifier>()
        var location: Location = NonExistLocation
        var origin: Origin = Origin.KOTLIN
        var parent: KSNode? = null

        fun build(): DummyKSClassDeclaration {
            return DummyKSClassDeclaration(
                annotations = annotations.asSequence(),
                classKind = classKind,
                isCompanionObject = isCompanionObject,
                primaryConstructor = primaryConstructor,
                superTypes = superTypes.asSequence(),
                containingFile = containingFile,
                docString = docString,
                packageName = packageName,
                parentDeclaration = parentDeclaration,
                qualifiedName = qualifiedName,
                simpleName = simpleName,
                typeParameters = typeParameters,
                declarations = declarations.asSequence(),
                isActual = isActual,
                isExpect = isExpect,
                modifiers = modifiers,
                location = location,
                origin = origin,
                parent = parent,
            )
        }
    }

    override fun <D, R> accept(visitor: KSVisitor<D, R>, data: D): R {
        TODO()
    }

    override fun asStarProjectedType(): KSType {
        TODO()
    }

    override fun asType(typeArguments: List<KSTypeArgument>): KSType {
        TODO()
    }

    override fun findActuals(): Sequence<KSDeclaration> {
        TODO()
    }

    override fun findExpects(): Sequence<KSDeclaration> {
        TODO()
    }

    override fun getAllFunctions(): Sequence<KSFunctionDeclaration> {
        TODO()
    }

    override fun getAllProperties(): Sequence<KSPropertyDeclaration> {
        TODO()
    }

    override fun getSealedSubclasses(): Sequence<KSClassDeclaration> {
        TODO()
    }
}

inline fun <reified T> dummyKSClassDeclaration(
    crossinline builder: DummyKSClassDeclaration.Builder.() -> Unit = {},
): DummyKSClassDeclaration {
    val simpleName = DummyKSName(
        T::class.java.simpleName,
    )
    val packageName = DummyKSName(
        T::class.java.`package`.name,
    )
    return dummyKSClassDeclaration(simpleName, packageName) {
        qualifiedName = T::class.qualifiedName?.let {
            DummyKSName(it)
        }
        builder()
    }
}

fun dummyKSClassDeclaration(
    simpleName: DummyKSName,
    packageName: DummyKSName,
    builder: DummyKSClassDeclaration.Builder.() -> Unit = {},
): DummyKSClassDeclaration {
    return DummyKSClassDeclaration.Builder(simpleName, packageName)
        .apply { builder() }
        .build()
}
