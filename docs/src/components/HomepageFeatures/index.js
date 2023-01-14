import React from 'react';
import clsx from 'clsx';
import styles from './styles.module.css';

const FeatureList = [
  {
    title: 'Easy to Use',
    Svg: require('@site/static/img/setup.svg').default,
    description: (
      <>
        Form conductor library makes form validations easier by using annotations.
        You can create a form instance in a few minutes without worrying about state handling.
      </>
    ),
  },
  {
    title: 'JVM based',
    Svg: require('@site/static/img/multiplatform.svg').default,
    description: (
      <>
        Even if you're not using Jetpack Compose or not working on android, you can still use the library for form validations in Kotlin Apps.
      </>
    ),
  },
  {
    title: 'Powered by Kotlin Reflection',
    Svg: require('@site/static/img/framework.svg').default,
    description: (
      <>
        The library is powered by Kotlin Reflection to process form's metadata under the hood.
        Works flawlessly with obfuscated/minified code in release builds.
      </>
    ),
  },
];

function Feature({Svg, title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} role="img" />
      </div>
      <div className="text--center padding-horiz--md">
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
